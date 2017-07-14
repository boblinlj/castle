package castle;

import java.util.Scanner;

public class Game {
    private Room currentRoom;
        
    public Game() 
    {
        createRooms();
    }

    private void createRooms()
    {
        Room outside, lobby, pub, study, bedroom;
      
        //	鍒堕�犳埧闂�
        outside = new Room("鍩庡牎澶�");
        lobby = new Room("澶у爞");
        pub = new Room("灏忛厭鍚�");
        study = new Room("涔︽埧");
        bedroom = new Room("鍗у");
        
        //	鍒濆鍖栨埧闂寸殑鍑哄彛
        outside.setExits(null, lobby, study, pub);
        lobby.setExits(null, null, null, outside);
        pub.setExits(null, outside, null, null);
        study.setExits(outside, bedroom, null, null);
        bedroom.setExits(null, null, null, study);

        currentRoom = outside;  //	浠庡煄鍫￠棬澶栧紑濮�
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("娆㈣繋鏉ュ埌鍩庡牎锛�");
        System.out.println("杩欐槸涓�涓秴绾ф棤鑱婄殑娓告垙銆�");
        System.out.println("濡傛灉闇�瑕佸府鍔╋紝璇疯緭鍏� 'help' 銆�");
        System.out.println();
        System.out.println("鐜板湪浣犲湪" + currentRoom);
        System.out.print("鍑哄彛鏈夛細");
        if(currentRoom.northExit != null)
            System.out.print("north ");
        if(currentRoom.eastExit != null)
            System.out.print("east ");
        if(currentRoom.southExit != null)
            System.out.print("south ");
        if(currentRoom.westExit != null)
            System.out.print("west ");
        System.out.println();
    }

    // 浠ヤ笅涓虹敤鎴峰懡浠�

    private void printHelp() 
    {
        System.out.print("杩疯矾浜嗗悧锛熶綘鍙互鍋氱殑鍛戒护鏈夛細go bye help");
        System.out.println("濡傦細\tgo east");
    }

    private void goRoom(String direction) 
    {
        Room nextRoom = null;
        if(direction.equals("north")) {
            nextRoom = currentRoom.northExit;
        }
        if(direction.equals("east")) {
            nextRoom = currentRoom.eastExit;
        }
        if(direction.equals("south")) {
            nextRoom = currentRoom.southExit;
        }
        if(direction.equals("west")) {
            nextRoom = currentRoom.westExit;
        }

        if (nextRoom == null) {
            System.out.println("閭ｉ噷娌℃湁闂紒");
        }
        else {
            currentRoom = nextRoom;
            System.out.println("浣犲湪" + currentRoom);
            System.out.print("鍑哄彛鏈�: ");
            if(currentRoom.northExit != null)
                System.out.print("north ");
            if(currentRoom.eastExit != null)
                System.out.print("east ");
            if(currentRoom.southExit != null)
                System.out.print("south ");
            if(currentRoom.westExit != null)
                System.out.print("west ");
            System.out.println();
        }
    }
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Game game = new Game();
		game.printWelcome();

        while ( true ) {
        		String line = in.nextLine();
        		String[] words = line.split(" ");
        		if ( words[0].equals("help") ) {
        			game.printHelp();
        		} else if (words[0].equals("go") ) {
        			game.goRoom(words[1]);
        		} else if ( words[0].equals("bye") ) {
        			break;
        		}
        }
        
        System.out.println("鎰熻阿鎮ㄧ殑鍏変复銆傚啀瑙侊紒");
        in.close();
	}

}
