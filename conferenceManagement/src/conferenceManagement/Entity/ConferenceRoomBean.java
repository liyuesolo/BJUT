package conferenceManagement.Entity;

public class ConferenceRoomBean {
    private int room_id;
    private String room_name;
    private int room_capacity;
    private int room_status = 0;
	private String room_remark;


    public int getRoomid() {
        return this.room_id;
    }

    public void setRoomid(int room_id) {
        this.room_id = room_id;
    }

    public String getRoomname() {
        return this.room_name;
    }

    public void setRoomname(String room_name) {
        this.room_name = room_name;
    }
	
	public int getRoomcapacity() {
        return this.room_capacity;
    }
	
    public void setRoomcapacity(int room_capacity) {
        this.room_capacity = room_capacity;
    }

    public int getRoomstatus() {
        return this.room_status;
    }

    public void setRoomstatus(int room_status) {
        this.room_status = room_status;
    }
	public String getRoomremark() {
        return this.room_remark;
    }

    public void setRoomremark(String room_remark) {
        this.room_remark = room_remark;
    }
    
}