/**
 * 
 */
public class MenuManager {

    /**
     * 
     * -1 -> Quit Game
     * 0  -> Main Menu
     * 1  -> Question Menu
     */
    int stage;

    /**
     * 
     */
    MenuMain mainMenu;

    /**
     * 
     */
    public MenuManager() {
        this.stage = 0;
        this.mainMenu = new MenuMain(); 
        int option;
        while (stage > 0) {
            if (stage == 0) {
                mainMenu.visibility(true);
                option = mainMenu.getOption();
                if (option == 1) stage = 1;
                if (option == 2) stage = -1;
            }
        }
        mainMenu.visibility(false);
    }


}
