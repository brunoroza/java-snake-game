package com.game.states;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Representa o estado no menu
 * 
 * @author Erick Zanardo
 *
 */
public class Menu extends BasicGameState {

    private int stateId;
    /** Imagem de fundo */
    private Image background;
    /** Op��es do menu */
    private String[] options;
    /** Ind�ce do menu selecionado */
    private int indice;

    public Menu(int stateId) {
        this.stateId = stateId;
    }

    public int getID() {
        return stateId;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {
        background = new Image("img/mainMenu.png");
        options = new String[3];
        options[0] = "Jogar";
        options[1] = "Configura��es";
        options[2] = "Sair";
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {
        background.draw(( Game.GAME_WIDTH - background.getWidth()) / 2, 50);

        int y = background.getHeight() + 150;
        for (int i = 0; i < options.length; i++) {
            if (i == indice) {
                g.setColor(Color.gray);
            }
            g.drawString(options[i], 300, y);
            g.setColor(Color.white);
            y += 20;
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {
        Input input = gc.getInput();
        // Move para cima
        if (input.isKeyPressed(Input.KEY_UP)) {
            if (indice > 0) {
                indice--;
            } else {
                indice = options.length - 1;
            }
        // Move para baixo
        } else if (input.isKeyPressed(Input.KEY_DOWN)) {
            if (indice < options.length - 1) {
                indice++;
            } else {
                indice = 0;
            }
        // Confirma a��o
        } else if (input.isKeyPressed(Input.KEY_ENTER)) {
            switch (indice) {
            // Jogar
            case 0:
                sbg.getState(Main.GAME).init(gc, sbg);
                sbg.enterState(Main.GAME);
                break;
            // Configura��o
            case 1:
                //TODO Implementar uma a��o quando criar a tela
                break;
            // Sair
            case 2:
                System.exit(0);
                break;
            }
        }
    }

}
