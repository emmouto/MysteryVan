package View;

import Controller.HighscoreController;
import Controller.ScreenController;
import Model.Highscore;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.resources.Resources;
import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * @author Antonia Welzel
 */
public class HighscoreView extends Screen implements IUpdateable {

    private static final BufferedImage brickBG = Resources.images().get("src/main/resources/HelpView/BrickBG.png");

    private int currentFocus = -1;
    private int previousFocus = 0;
    public static long lastMenuInput;

    Highscore h[] = new Highscore[10]; //{h1, h2, h3, h4, h5, h6, h7, h8, h9, h10};

    private ScreenController screenController;
    private HighscoreController hc = new HighscoreController(h);

    public HighscoreView(String screenName) {

        super(screenName);
    }

    protected void initializeComponents() {

        this.screenController = new ScreenController(0, 0, 0, 0, "");
        this.getComponents().add(this.screenController);

        this.screenController.onConfirm(c -> this.showMenu());
    }

    @Override
    public void render(final Graphics2D g) {

        ImageRenderer.render(g, brickBG, 0, 0);
        g.setColor(Color.BLACK);
        g.fillRect(240,130,700,580);
        g.setColor(Color.WHITE);
        g.drawRect(240,130,700,580);

        g.setFont(Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf", 58f));
        g.setColor(Color.MAGENTA);
        TextRenderer.render(g, "HIGHSCORES", 320, 100);

        g.setFont(Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf", 30f));
        g.setColor(Color.CYAN);
        TextRenderer.render(g, "PLAYER", 320, 190);
        g.setColor(Color.ORANGE);
        TextRenderer.render(g, "SCORE", 720, 190);


        g.setColor(Color.WHITE);
        g.setFont(Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf", 26f));
        int y = 190;
        for (Highscore hs : h) {
            TextRenderer.render(g, hs.getPlayer(), 320, (y + 50));
            TextRenderer.render(g, Integer.toString(hs.getHighscore()), 720, (y + 50));
            y += 50;
        }

        g.setColor(Color.PINK);
        g.setFont(Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf", 32f));
        TextRenderer.render(g, "Menu", 100, 100);

        this.onHovered(componentMouseEvent -> {g.setColor(Color.orange);
        TextRenderer.render(g, "Menu", 100, 100); });



        this.onClicked(componentMouseEvent -> this.showMenu());
/*
        //TODO need change of colour when mouse hovers
        this.onMouseLeave(e -> {
            g.setColor(Color.orange);
            TextRenderer.render(g, "Menuuu", 100, 150);
        });

        this.onMouseEnter(e -> {
            g.setColor(Color.yellow);
            TextRenderer.render(g, "Menuuu", 100, 150);
        });

*/

        /*this.onHovered(componentMouseEvent -> {

            this.currentFocus = this.getComponents().indexOf(this);
            this.updateFocus();
            //this.getAppearance().setBackgroundColor1(Color.white);
        });
        */


        super.render(g);
    }





    /**
     * Method to call when current screen is changed to menu screen.
     */
    private void showMenu() {
        Game.screens().display("Menu");
    }


    //all below not used


    public void update() {

    }


    private void decFocus() {
        this.currentFocus = Math.floorMod(--this.currentFocus, this.getComponents().size());
        this.updateFocus();
    }

    private void incFocus() {
        this.currentFocus = ++this.currentFocus % this.getComponents().size();
        this.updateFocus();
    }

    protected void updateFocus() {
        for (int i = 0; i < this.getComponents().size(); i++) {
            this.getComponents().get(i).setHovered(i == this.currentFocus);
        }

        if (this.currentFocus != this.previousFocus) {
            this.previousFocus = this.currentFocus;
        }

        lastMenuInput = Game.inputLoop().getTicks();

        //if (this.isVisible()) {
            //Game.audio().playSound(ArrayUtilities.getRandom(SETTING_HOVER_SOUNDS));
        //}
    }


    /*
    public void initializeComponents() {


        this.hcontrol = new HighscoreController(100, 100, 100, 100, "Menu");

        //this.setFont(Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",26f));
        //this.setForwardMouseEvents(false);
        //this.getComponents().forEach(guiComponent -> guiComponent.setForwardMouseEvents(false));

        //if(!this.getComponents().isEmpty()) {
          //  this.getComponents().get(0).setHovered(true);
        //}

        //this.getComponents().forEach(guiComponent -> {
          //  guiComponent.setFont(Resources.fonts().get("src/main/resources/fonts/Pixeled.ttf",26f));
            //guiComponent.getAppearance().setForeColor(Color.ORANGE);
            //guiComponent.getAppearance().setTransparentBackground(true);
           // guiComponent.getAppearanceHovered().setTransparentBackground(true);
           // guiComponent.getAppearanceHovered().setForeColor(Color.cyan);
       // });

        //this.getComponents().get(0).setX(100);
        //this.getComponents().get(0).setY(100);

       // this.hcontrol.onConfirm(c -> {this.showMenu();});


    }
     */


    public void prepare() {
        //this.hcontrol.setEnabled(true);
        super.prepare();
        //Game.loop().attach(this);
        //Game.graphics().setBaseRenderScale(6f * Game.window().getResolutionScale());
        //this.hcontrol.incFocus();
        if (!this.getComponents().isEmpty()) {
            this.currentFocus = 0;
            this.getComponents().get(0).setHovered(true);
        }

    }


    }




/*
    String[] colName = {"Player", "Points"};
    Object[][] data = {{player1,p1}, {player2,p2}, {player3,p3}, {player4,p4}, {player5,p5},
                        {player6,p6}, {player7,p7}, {player8,p8}, {player9,p9}, {player10,p10}};

    JTable table = new JTable(data, colName);

    JScrollPane scrollPane = new JScrollPane(table);


*/

    /*

    //Player names
    String player1 = "hello";
    String player2 = null;
    String player3 = null;
    String player4 = null;
    String player5 = null;
    String player6 = null;
    String player7 = null;
    String player8 = null;
    String player9 = null;
    String player10 = null;

    //Player scores
    int p1 = 0;
    int p2 = 0;
    int p3 = 0;
    int p4 = 0;
    int p5 = 0;
    int p6 = 0;
    int p7 = 0;
    int p8 = 0;
    int p9 = 0;
    int p10 = 0;

    //Highscores
    Highscore h1 = new Highscore(p1, player1);
    Highscore h2 = new Highscore(p2, player2);
    Highscore h3 = new Highscore(p3, player3);
    Highscore h4 = new Highscore(p4, player4);
    Highscore h5 = new Highscore(p5, player5);
    Highscore h6 = new Highscore(p6, player6);
    Highscore h7 = new Highscore(p7, player7);
    Highscore h8 = new Highscore(p8, player8);
    Highscore h9 = new Highscore(p9, player9);
    Highscore h10 = new Highscore(p10, player10);
    */


