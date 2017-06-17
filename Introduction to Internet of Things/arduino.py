import serial
import pygame
import pygame.camera
from pygame.locals import *
import urllib2
import urllib
import time
import json

http_url='https://api-cn.faceplusplus.com/facepp/v3/detect'
key = "Face++ API Key"
secret = "Face++ API Secret"
attributes = "age"

baud = 115200
arduino = serial.Serial('/dev/ttyACM0', baud)

pygame.init()
pygame.camera.init()
cam = pygame.camera.Camera("/dev/video0",(640,480))
cam.start()
with arduino:
	while True:
		if arduino.read(1) == 'c':
			image = cam.get_image()
			pygame.image.save(image, "image.jpg")
			time.sleep(5)
			filepath = r"image.jpg"
			boundary = '----------%s' % hex(int(time.time() * 1000))
			data = []
			data.append('--%s' % boundary)
			data.append('Content-Disposition: form-data; name="%s"\r\n' % 'api_key')
			data.append(key)
			data.append('--%s' % boundary)
			data.append('Content-Disposition: form-data; name="%s"\r\n' % 'api_secret')
			data.append(secret)
			data.append('--%s' % boundary)
			data.append('Content-Disposition: form-data; name="%s"\r\n' % 'return_attributes')
			data.append(attributes)
			data.append('--%s' % boundary)
			fr=open(filepath,'rb')
			data.append('Content-Disposition: form-data; name="%s"; filename=" "' % 'image_file')
			data.append('Content-Type: %s\r\n' % 'application/octet-stream')
			data.append(fr.read())
			fr.close()
			data.append('--%s--\r\n' % boundary)

			http_body='\r\n'.join(data)
			#buld http request
			req=urllib2.Request(http_url)
			#header
			req.add_header('Content-Type', 'multipart/form-data; boundary=%s' % boundary)
			req.add_data(http_body)
			try:
				#req.add_header('Referer','http://remotserver.com/')
				#post data to server
				resp = urllib2.urlopen(req, timeout=5)
				#get response
				qrcont=resp.read()
				age = json.loads(qrcont)['faces'][0]['attributes']['age']['value']
				print age
				if (int(age)>0 and int(age) < 14) or (int(age) > 65):
					arduino.write('3')
				elif (int(age) > 18 and int(age) <35):
					arduino.write('1')
				else:
					arduino.write('2')

			except urllib2.HTTPError as e:
			    print e.read()
						