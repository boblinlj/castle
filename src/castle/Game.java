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
				System.out.print("��·������������������У�go bye help");
			    System.out.println("�磺\tgo east");
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

        //  ���췿��
        outside = new Room("�Ǳ���");
        lobby = new Room("����");
        pub = new Room("С�ư�");
        study = new Room("�鷿");
        bedroom = new Room("����");

        //  ��ʼ������ĳ���(ps:���Է���ļ����ɾ���κη���)
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

        currentRoom = outside;  //  �ӳǱ����⿪ʼ
	}
	
	private void printWelcome(){
		System.out.println();
        System.out.println("��ӭ�����Ǳ���");
        
        System.out.println("����һ���������ĵ���Ϸ��");
        System.out.println("�����Ҫ������������ 'help' ��");
        System.out.println();
        showPrompt();
	}

	
	private void showPrompt(){
		System.out.println("����" + currentRoom);
        System.out.print("������: ");
        System.out.println(currentRoom.getExitDesc());
        System.out.println();
	}
	
	public void goRoom(String direction){
		Room nextRoom = currentRoom.goExit(direction);
		if(nextRoom == null){
			System.out.println("����û���ţ�");
		}else{
			currentRoom = nextRoom;
			showPrompt();
		}
	}
	
	public static void main(String[] args) {
		
        Game game = new Game();
        game.printWelcome();
        game.play();

        System.out.println("��л���Ĺ��١��ټ���");	
    }

}
