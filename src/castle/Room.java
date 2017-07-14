package castle;

public class Room {
    private String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;

    public Room(String description) 
    {
        this.description = description;
    }
    
    public Room goExit(String direction) {
        Room nextRoom = null;
        if(direction.equals("north")) {
            nextRoom =  northExit;
        }
        if(direction.equals("east")) {
            nextRoom =  eastExit;
        }
        if(direction.equals("south")) {
            nextRoom =  southExit;
        }
        if(direction.equals("west")) {
            nextRoom =  westExit;
        }
        return  nextRoom;
    }

    public void setExits(Room north, Room east, Room south, Room west) 
    {
        if(north != null)
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
    }
    
    public String getExitDesc() {
        StringBuffer sb = new StringBuffer();
        if( northExit != null) 
            sb.append(" north");
        if( eastExit != null) 
            sb.append(" east");
        if( southExit != null) 
           sb.append(" south");
        if( westExit != null) 
           sb.append(" weat");
        System.out.println();
        
        return sb.toString();
 }

    @Override
    public String toString()
    {
        return description;
    }
}
