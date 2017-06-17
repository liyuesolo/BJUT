package com.example.liyue.howold;


import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private static final int PICK_CODE = 0x1;//request code
    private static final int TAKE_PHOTO = 0x2;
    private ImageView ivPhoto;
    private Button bGetImage;
    private Button bDetect;
    private Button bTakePhoto;
    private TextView tvTip;
    private View vWaitting;
    private Bitmap bmPhotoImg;
    private Paint paint;
    private String currentPhotoPath;
    private static final int MSG_SUCCESS = 0xfff;
    private static final int MSG_ERROR = 0x000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initEvents();

        paint = new Paint();
    }

    private void initViews()
    {
        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
        bGetImage = (Button) findViewById(R.id.bGetImage);
        bDetect = (Button) findViewById(R.id.bDetect);
        bTakePhoto = (Button) findViewById(R.id.bTakePhoto);
        tvTip = (TextView) findViewById(R.id.tvTip);
        vWaitting = findViewById(R.id.flWaitting);
    }

    private void initEvents()
    {
        bGetImage.setOnClickListener(this);
        bDetect.setOnClickListener(this);
        bTakePhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bGetImage:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_CODE);
                break;

            case R.id.bTakePhoto:
                Intent intentTp = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentTp, TAKE_PHOTO);
                break;

            case R.id.bDetect:
                //show progress bar
                vWaitting.setVisibility(View.VISIBLE);

                //if photo is selected
                if (currentPhotoPath == null || currentPhotoPath.trim().equals("")) //trim is used to delete tab space empty words at both sides
                {
                    //set photo img to default image
                    bmPhotoImg = BitmapFactory.decodeResource(getResources(), R.drawable.demopic6);
                }


                FaceppDetect.detect(bmPhotoImg, new FaceppDetect.CallBack()
                {
                    @Override
                    public void success(JSONObject jsonObject)
                    {
                        //System.out.println(str);
                        Message msg = Message.obtain();
                        msg.what = MSG_SUCCESS;
                        msg.obj = jsonObject;
                        handler.sendMessage(msg);

                    }

                    @Override
                    public void error(Exception exception)
                    {
                        Message msg = Message.obtain();
                        msg.what = MSG_ERROR;
                        msg.obj = exception.getMessage();
                        handler.sendMessage(msg);
                    }
                });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if (requestCode == PICK_CODE)
        {
            if (intent != null)
            {
                Uri uri = intent.getData();
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                //move cursor to the first row
                cursor.moveToFirst();
                //get column index and value
                //get the index for the current selection
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                currentPhotoPath = cursor.getString(idx);
                cursor.close();
                //get picture from path
                //Face++ sdk requires picture less that 3M thus resize the photo
                resizePhoto();
                ivPhoto.setImageBitmap(bmPhotoImg);
                tvTip.setText("Click Detect ->");
            }
        }
        else if (requestCode == TAKE_PHOTO)
        {
            if(intent != null)
            {
                Bitmap bmPhoto = (Bitmap) intent.getExtras().get("data");
                currentPhotoPath = "image_capture";
                bmPhotoImg = bmPhoto;
                ivPhoto.setImageBitmap(bmPhotoImg);
                tvTip.setText("Click Detect ==>");
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    //child thread send message to main thread which put it into it's message queue and then update UI thread
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case MSG_SUCCESS:
                    vWaitting.setVisibility(View.GONE);//hide progress bar
                    JSONObject jsonObject = (JSONObject) msg.obj;

                    prepareResultBitmap(jsonObject);

                    // save plot result into photo image
                    ivPhoto.setImageBitmap(bmPhotoImg);

                    break;
                case MSG_ERROR:
                    vWaitting.setVisibility(View.GONE);
                    String errorMSG = (String) msg.obj;

                    if (TextUtils.isEmpty(errorMSG))
                    {
                        tvTip.setText("Error !");
                    }
                    else
                    {
                        tvTip.setText(errorMSG);
                    }

                    break;
            }
            super.handleMessage(msg);
        }
    };

    private void prepareResultBitmap(JSONObject jsonObject)
    {
        Bitmap bitmap = Bitmap.createBitmap(bmPhotoImg.getWidth(), bmPhotoImg.getHeight(), bmPhotoImg.getConfig());
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(bmPhotoImg, 0, 0, null); // draw original image to bitmap
        try
        {
            JSONArray faces = jsonObject.getJSONArray("faces");

            int faceNum = faces.length();

            tvTip.setText("Find "+ faceNum + " face(s)");

            for (int i = 0; i < faceNum; i++)
            {
                //get each face objects
                JSONObject face = faces.getJSONObject(i);
                JSONObject faceRectangle = face.getJSONObject("face_rectangle");

                int age = face.getJSONObject("attributes").getJSONObject("age").getInt("value");
                String gender = face.getJSONObject("attributes").getJSONObject("gender").getString("value");

                int height = faceRectangle.getInt("height");
                int width = faceRectangle.getInt("width");
                int left = faceRectangle.getInt("left");
                int top = faceRectangle.getInt("top");

                paint.setColor(0xffffffff);
                paint.setStrokeWidth(3);
                //draw bow
                canvas.drawLine(left, top, left, top + height, paint);
                canvas.drawLine(left, top, left + width, top, paint);
                canvas.drawLine(left + width, top, left + width, top + height, paint);
                canvas.drawLine(left, top + height, left + width, top + height, paint);

                Bitmap bmAgeGender = buildBitmap(age, gender.equals("Male"));

                //scale testview bitmap
                int ageWidth = bmAgeGender.getWidth();
                int ageHeight = bmAgeGender.getHeight();

                if (bitmap.getWidth() < ivPhoto.getWidth() && bitmap.getHeight() < ivPhoto.getHeight())
                {
                    float ratio = Math.max(bitmap.getWidth() * 1.0f / ivPhoto.getWidth(),
                            bitmap.getHeight() * 1.0f / ivPhoto.getHeight());
                    bmAgeGender = Bitmap.createScaledBitmap(bmAgeGender, (int) (ageWidth * ratio), (int) (ageHeight * ratio), false);

                }
                canvas.drawBitmap(bmAgeGender, left + width /2 - bmAgeGender.getWidth() / 2 , top - bmAgeGender.getHeight(), null);
                bmPhotoImg = bitmap; // assign bitmap as new bitmap

            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    private Bitmap buildBitmap(int age, boolean male)
    {
        TextView tvAgeGender = (TextView) vWaitting.findViewById(R.id.tvAgeGender);
        tvAgeGender.setText(age + " ");
        if (male)
        {
            //get male.png from /drawable directory
            tvAgeGender.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.male), null, null, null);
        }
        else
        {
            //get male.png from /drawable directory
            tvAgeGender.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.female), null, null, null);
        }

        tvAgeGender.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(tvAgeGender.getDrawingCache());
        tvAgeGender.destroyDrawingCache();

        return bitmap;
    }

    //resize photo
    private void resizePhoto()
    {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; //get the size of the photo without loading

        BitmapFactory.decodeFile(currentPhotoPath, options);

        double ratio = Math.max(options.outWidth * 1.0d / 1024f, options.outHeight * 1.0d / 1024f);

        options.inSampleSize= (int) Math.ceil(ratio);

        options.inJustDecodeBounds = false; //load this picture now

        bmPhotoImg = BitmapFactory.decodeFile(currentPhotoPath, options);

    }


}
