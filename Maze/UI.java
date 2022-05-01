package Maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.ColorUIResource;

import java.awt.*;

public class UI{
    private Frame canvas;


    private Button xChoice;
    private Button oChoice;
    private Button restartButton;
    private Button startFirst;
    private Button startSecond;
    private Button medium;
    private Button easy;
    
    private String userChoice, AIChoice;
    private int turnChoice;
    private int difficulty;
    
    private GraphicsGroup uiGroup;

    private int moveCounter; 


    boolean AIFirst = false;
    
    public UI(){
        canvas = new CanvasWindow("Tic,Tac,Toe", 600, 600);

        ai = new ChessAI(arrayMap, arrayX, arrayO);
        moves = new ArrayList<>();
        setupUI();
        chooseSide();
        turnChoice = 0;
        choseFirstPlayer();
     

        canvas.add(uiGroup, 300, 30);
        chooseDifficulty();
        difficulty = 0;
   
        play();
    }

    /**
     * Places the choose piece (X or O) button and choice labels on the canvas window
     */
    private void chooseSide() {
        choiceLabel = new GraphicsText("Choose X or O: ");
        choiceLabel.setFont("Helvetica", FontStyle.BOLD, 20);
        canvas.add(choiceLabel, 20, 50);

        xChoice = new Button("X");
        oChoice = new Button("O");

        uiGroup = new GraphicsGroup();

        uiGroup.add(xChoice);
        uiGroup.add(oChoice, xChoice.getX() + xChoice.getWidth() + 25, xChoice.getY());

        xChoice.onClick(() -> {
            if(userChoice == null) {
                userChoice = "X";
                AIChoice = "O";
                uiGroup.remove(oChoice);
                if(turnChoice == 2 && moveCounter == 0 && difficulty != 0) {
                    AIMove(); // Ai will move and place "O" after the user has clicked a block and chosen "O"
                }
            }  
            
        });

        oChoice.onClick(() -> {
            if(userChoice == null) {
                userChoice = "O";
                AIChoice = "X";
                uiGroup.remove(xChoice);
                if(turnChoice == 2 && moveCounter == 0 && difficulty !=0) {
                    AIMove(); //Ai will move and place "X" if the user has clocked a bock and chosen "O"
                }
            } 
            
        });

    }
    /**
     * Places the label that asks the user to pick who goes first (Human or AI) along with the turn buttons
     */
    private void choseFirstPlayer() {
        turnLabel = new GraphicsText("Who plays first? ");
        turnLabel.setFont("Helvetica", FontStyle.BOLD, 20);
        canvas.add(turnLabel, 20, 80);

        startFirst = new Button("Human");
        startSecond = new Button("AI");


        uiGroup.add(startFirst, xChoice.getX(), 30);
        uiGroup.add(startSecond, oChoice.getX(), startFirst.getY());

        startFirst.onClick(() -> {
            if(turnChoice == 0) {
                turnChoice = 1;
                uiGroup.remove(startSecond); //Removes start second button if user chose to start first for clarity
            }

        });

        startSecond.onClick(() -> {
            AIFirst = true;
            if(turnChoice == 0) {
                turnChoice = 2;
                uiGroup.remove(startFirst); // Removes start first button if user chose to start second, for clarity
            }
            if(userChoice != null && moveCounter == 0 && difficulty !=0) {
                AIMove();
            }
        });
    }


    /**
     * Places the choose dificulty label (easy or hard) and the difficulty buttons on the canvas
     */
    private void chooseDifficulty(){
        difficultyLevelLabel = new GraphicsText("Choose your difficulty level:");
        difficultyLevelLabel.setFont("Helvetica", FontStyle.BOLD, 20);
        canvas.add(difficultyLevelLabel, 20, 110);


        medium = new Button("Difficult");
        easy = new Button("Easy");

        medium.onClick(() -> {
            difficulty = 2;
            if(turnChoice == 2 && moveCounter == 0 && userChoice != "") {
                AIMove();
            }
            uiGroup.remove(easy);
        });

        easy.onClick(() -> {
            difficulty = 1;
            if(turnChoice == 2 && moveCounter == 0 && userChoice != null) {
                AIMove();
            }
            uiGroup.remove(medium);
        });
        
        uiGroup.add(easy, xChoice.getX() , 60);
        uiGroup.add(medium, oChoice.getX(), easy.getY());
        

    }

    /**
     * Sets up the user interferance, adds the images, buttons, labels and board to the canvas
     */
    private void setupUI(){
        backgroundTable.setScale(1.5,2);
        canvas.add(backgroundTable);
        cupOfCoffee.setScale(0.4, 0.4);
        canvas.add(cupOfCoffee, 250,1);
        
        macLogo.setScale(0.45, 0.45);
        canvas.add(macLogo,65,35);
        
        restartButton = new Button("Restart");
        GraphicsGroup restart = new GraphicsGroup();
        restart.add(restartButton);
        canvas.add(restart, 150, 380);

        restartButton.onClick(() -> {
            restart();
        });

        int xPos = 225;
        int yPos = 200;
        int xCord = 0;
        int yCord = 0;
        squares = new ArrayList<>();

        for(int i=0; i<9; i++) {
            if(i%3==0) {
                xPos = 250; 
                yPos += 20;
                xCord = 0;
                if( i != 0) {
                    yCord++;
                }
            } else {
                xPos += 20;
                xCord++;
            }

            Block block = new Block(new Coord(xCord,yCord));
            block.getRectangle().setFillColor(new ColorUIResource(0,102,204));
            
            canvas.add(block.getGroup(), xPos, yPos);
            squares.add(block);
        }
    }

     /**
     * Cleans up the canvas by removing all graphics, then calls all constructores once again to re-start the game
     */
    private void restart(){
        canvas.removeAll();
        userChoice = null;
        AIChoice = null;
        turnChoice = 0;
        moveCounter = 0;
        difficulty = 0;
        moves = new ArrayList<>();
        setupUI();

        emptyArray(array);


        

        for (String[][] array: arrayMap.values()) {
            emptyArray(array);
        }

        ai.setDrawStatus();
        ai.setWinStatus();
        ai.setWinningSide();
        
        chooseSide();
        choseFirstPlayer();
        canvas.add(uiGroup, 300, 30);

        chooseDifficulty();

        play();
    }

    /**
     * Empties out the array for reseting the board
     * @param array Array to be resetted
     */
    private void emptyArray(String[][] array) {
        for(int i = 0; i<3; i++) {
            for(int j =0; j<3; j++) {
                array[i][j] = "";
            }
        }
    }

    /**
     * Integrates the AI and GUI
     * Records the user's movement on the board and integrates the AI to analyze the board after
     */
    private void play() {
        if (!ai.getDrawStatus() && !ai.getWinStatus()) canvas.onClick(event -> {
            for(Block block : squares) {
                GraphicsGroup group = block.getGroup();
                if (group.testHit(event.getPosition().getX(), event.getPosition().getY())) {
                    if(userChoice != null && turnChoice != 0 && difficulty !=0 && !moves.contains(block.getCoord()) && (!ai.getWinStatus() && !ai.getDrawStatus())) {
                        moves.add(block.getCoord());
                        GraphicsText text = new GraphicsText(userChoice, group.getCenter().getX(), group.getCenter().getY());
                        text.setFillColor(new ColorUIResource(255, 255, 255));
                        canvas.add(text);
                        canvas.draw();
                        int xCoord = block.getCoord().getX();
                        int yCoord = block.getCoord().getY();
                        

                        ai.logHumanPiece(array, xCoord, yCoord, userChoice);

                        ai.checkWinning(arrayMap.get(userChoice), AIChoice, userChoice);
                        ai.checkDraw(array);

                        moveCounter++;

                        if(moveCounter < 9 && (!ai.getWinStatus() || !ai.getDrawStatus())) {
                            AIMove();
                        }
                        ai.checkWinning(arrayMap.get(AIChoice), AIChoice, userChoice);
                        ai.checkDraw(array);
                    }
                }
            }

        // Places images and text labels on board to let the user know the game has ended, in either a draw or
        // there was a winner (Ai or Human)

        if (ai.getWinningSide() == "Draw") {
            noOneWins.setScale(0.3,0.3);
            GraphicsText drawText = new GraphicsText("DRAW.");
            drawText.setFont(FontStyle.BOLD_ITALIC,30);
            canvas.add(drawText, 300, 500);
            canvas.add(noOneWins, 250,270);
        }

            if (ai.getWinningSide() == "Human"){
                userWins.setScale(1.5, 1.5);
                canvas.add(userWins,300,400);
            } 
            if (ai.getWinningSide() == "AI") {
                GraphicsText aiWon = new GraphicsText("AI WINS!");
                aiWon.setFont(FontStyle.BOLD_ITALIC,30);
                canvas.add(aiWon, 320, 500);
                canvas.add(aiWins,400,400);
            }
        });

    }

    /**
     * Makes AI move using ChessAI class and integrates the AI and GUI.
     */
    private void AIMove() {
        if(difficulty == 2) { //determnies the AI movements depending on the difficulty level chosen by the user
            ai.strategy(array, AIChoice, userChoice, moveCounter, AIFirst);
        } else if (difficulty == 1){
            ai.randomChess(array, AIChoice); // if the user chose "easy" the AI will just place pieces randomly on the board
        }

        for (Block bl : squares) {
            if (bl.getCoord().getX() == ai.getCoordToUpdate().getX() && bl.getCoord().getY() == ai.getCoordToUpdate().getY()) {
                GraphicsGroup group = bl.getGroup();
                GraphicsText text = new GraphicsText(AIChoice, group.getCenter().getX(), group.getCenter().getY());
                text.setFillColor(new ColorUIResource(255, 255, 255));
                canvas.add(text);
                canvas.draw();
                moves.add(bl.getCoord());

            }
        }

        moveCounter++;

        
    }

   

    public static void main(String[] args){
        GameApp window = new GameApp();
    }
}
