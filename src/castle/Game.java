package castle;

import java.util.HashMap;
import java.util.Scanner;



// initiate the game
public class Game {
	
	private Room currentRoom;
	private HashMap<String, Handler> handlers = new HashMap<String, Handler>();
	
	public Game(){
		createRooms();
		
		//inner class, which is used to create commend options
		handlers.put("go", new Handler(){

			@Override
			public void doCmd(String cmd) {
				goRoom(cmd);
			}
			
		});
		
		handlers.put("help", new Handler(){

			@Override
			public void doCmd(String cmd) {
				System.out.print("迷路了吗？你可以做的命令有：go bye help");
			    System.out.println("如：\tgo east");
			}

		});
		
		handlers.put("bye", new Handler(){

			@Override
			public boolean isBye() {
				return true;
			}
			
		});
		
	}
	
	public void play(){
        Scanner in = new Scanner(System.in);
        while ( true ) {
            String line = in.nextLine();
            String[] words = line.split(" ");
            
            Handler handler = handlers.get(words[0]);
            String value = "";
            if (words.length >1 ){
            	value  = words[1];
            }
            if ( handler != null ) {
                handler.doCmd(value);
                if(handler.isBye()){
                	break;
                }
            }

        }            
        in.close();
	}
	
	public void createRooms(){
		Room outside, lobby, pub, study, bedroom;

        //  制造房间
        outside = new Room("城堡外");
        lobby = new Room("大堂");
        pub = new Room("小酒吧");
        study = new Room("书房");
        bedroom = new Room("卧室");

        //  初始化房间的出口(ps:可以方便的加入或删除任何方向)
        outside.setExits("east", lobby);
        outside.setExits("south", study);
        outside.setExits("west", pub);
        lobby.setExits("west", outside);
        pub.setExits("east", outside);
        study.setExits("north", outside);
        study.setExits("east", bedroom);
        bedroom.setExits("west", study);
        lobby.setExits("up", pub);
        pub.setExits("down", lobby);

        currentRoom = outside;  //  从城堡门外开始
	}
	
	private void printWelcome(){
		System.out.println();
        System.out.println("欢迎来到城堡！");
        
        System.out.println("这是一个超级无聊的游戏。");
        System.out.println("如果需要帮助，请输入 'help' 。");
        System.out.println();
        showPrompt();
	}

	
	private void showPrompt(){
		System.out.println("你在" + currentRoom);
        System.out.print("出口有: ");
        System.out.println(currentRoom.getExitDesc());
        System.out.println();
	}
	
	public void goRoom(String direction){
		Room nextRoom = currentRoom.goExit(direction);
		if(nextRoom == null){
			System.out.println("那里没有门！");
		}else{
			currentRoom = nextRoom;
			showPrompt();
		}
	}
	
	public static void main(String[] args) {
		
        Game game = new Game();
        game.printWelcome();
        game.play();

        System.out.println("感谢您的光临。再见！");	
    }

}
