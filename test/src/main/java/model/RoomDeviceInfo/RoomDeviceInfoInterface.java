package model.RoomDeviceInfo;

import java.util.List;

public interface RoomDeviceInfoInterface {
	
	public abstract List<RoomDeviceInfoBean> selectByRoomCode(int roomCode);

}
