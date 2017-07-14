package castle;

import java.util.HashMap;

public class Room {
    private String description;
  private HashMap<String, Room> exits = new HashMap<String, Room>(); //用HashMap表示方向

    public Room(String description) 
    {
        this.description = description;
    }

    public void setExits(String dir, Room room) {
        exits.put(dir, room);//调用put方法填写元素
    }

    @Override
    public String toString()
    {
        return description;
    }

    public String getExitDesc() {
        StringBuffer sb = new StringBuffer();
        for(String dir : exits.keySet()) {
            sb.append(dir);
            sb.append(' ');
        }
        return sb.toString();
    }

    public Room goExit(String direction) {   
        return exits.get(direction);
    }
}
