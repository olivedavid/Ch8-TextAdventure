/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  
 *  
 *  Player must find their way trough the World of Zuul and obtain the treasure.
 *  Certain items must be obtained in order to succeed.  
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Olive Tamondong
 * @version 2017.11.24
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;

        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();

    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, gates, market, 
              armory, blacksmith, tavern,
              theater, physician, inn, 
              field, stable, forest, 
              lake, palace, treasury;
      
        // create the rooms
        outside = new Room("outside the main gates of a kingdom");
        gates = new Room(" at the gates entering the kingdom ");
        market = new Room("in the market");
        armory = new Room("in the armory");
        blacksmith = new Room("in the blacksmith's shop");
        tavern = new Room("in a tavern");
        theater = new Room("in a theater");
        physician = new Room("in the physicians home");
        inn = new Room("in the inn");
        field = new Room("in a field");
        stable = new Room("in a stable");
        forest = new Room("in a forest");
        lake = new Room("in a lake");
        palace = new Room("in the palace");
        treasury = new Room("in the treasury");
        
        // initialise room exits
        outside.setExit("north", gates);
        
        gates.setExit("north", market);
        gates.setExit("south", outside);
        
        market.setExit("north", theater);
        market.setExit("south", gates);
        
        armory.setExit("north", physician);
        armory.setExit("east", blacksmith);
        
        blacksmith.setExit("north", inn);
        blacksmith.setExit("west", armory);
        
        tavern.setExit("east", theater);
        
        theater.setExit("east", physician);
        theater.setExit("south", market);
        theater.setExit("west", tavern);
        
        physician.setExit("east", inn);
        physician.setExit("south", armory);
        physician.setExit("west", theater);
        
        inn.setExit("north", forest);
        inn.setExit("south", blacksmith);
        inn.setExit("west", physician);
        
        field.setExit("north", lake);
        field.setExit("east", stable);
        
        stable.setExit("east", forest);
        stable.setExit("west", field);

        forest.setExit("south", inn);
        forest.setExit("west", stable);

        lake.setExit("east", palace);
        lake.setExit("south", field);
        
        palace.setExit("north", treasury);
        palace.setExit("east", lake);
        
        treasury.setExit("south", palace);
        

        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring treasure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;
                
            case LOOK:
                look();
                break;
                
            case EAT:
                eat();
                break;
          

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are in search for the greatest treasure in the World of Zuul./n " + 
                            "You are alone. You wander");
        System.out.println("around at the kingdom.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }
    
    /**
     * "look" was entered. Look around the room for items, monsters, etc.
     *  Print what is inside the room
     */
    
    private void look()
    {
     System.out.println(currentRoom.getLongDescription());   
    }
    
    /**
     * "eat" was entered
     */
    
    private void eat()
    {
     System.out.println("You have eaten now and you are not hungry anymore");   
    }
    
    

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
