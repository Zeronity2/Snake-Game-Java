import processing.core.PApplet;

public class GameUI {

    int backgroundColor = 30;
    int whiteColor = 255;


    // =========================
    // START SCREEN
    // =========================

    public void drawStartScreen(PApplet app, int highScore) {

        app.background(backgroundColor);

        app.textAlign(PApplet.CENTER, PApplet.CENTER);

        app.fill(0, 255, 0);
        app.textSize(60);

        app.text(
            "SNAKE",
            app.width / 2,
            75
        );

        app.fill(200);
        app.textSize(18);

        app.text(
            "Classic Snake Game",
            app.width / 2,
            125
        );

        boolean hovering = isPlayButtonHovered(app);

        if (hovering) {
            app.cursor(PApplet.HAND);
        } else {
            app.cursor(PApplet.ARROW);
        }

        drawButton(
            app,
            app.width / 2,
            185,
            200,
            55,
            "PLAY",
            hovering
        );

        app.fill(255);
        app.textSize(17);

        app.text(
            "CONTROLS",
            app.width / 2,
            260
        );

        app.fill(180);
        app.textSize(15);

        app.text(
            "↑  ↓  ←  →   Move",
            app.width / 2,
            285
        );

        app.text(
            "P   Pause / Resume",
            app.width / 2,
            307
        );

        app.fill(255, 220, 80);
        app.textSize(18);

        app.text(
            "HIGH SCORE: " + highScore,
            app.width / 2,
            355
        );
    }


    // =========================
    // BUTTON
    // =========================

    public void drawButton(
            PApplet app,
            float x,
            float y,
            float width,
            float height,
            String label,
            boolean hovering) {

        app.rectMode(PApplet.CENTER);

        if (hovering) {
            app.fill(0, 240, 120);
        } else {
            app.fill(0, 200, 100);
        }

        app.rect(
            x,
            y,
            width,
            height,
            12
        );

        app.fill(255);

        app.textAlign(
            PApplet.CENTER,
            PApplet.CENTER
        );

        app.textSize(22);

        app.text(
            label,
            x,
            y
        );

        app.rectMode(PApplet.CORNER);
    }


    // =========================
    // PLAY BUTTON HOVER
    // =========================

    public boolean isPlayButtonHovered(PApplet app) {

        float buttonX = app.width / 2;
        float buttonY = 185;

        float buttonWidth = 200;
        float buttonHeight = 55;

        return app.mouseX > buttonX - buttonWidth / 2 &&
               app.mouseX < buttonX + buttonWidth / 2 &&
               app.mouseY > buttonY - buttonHeight / 2 &&
               app.mouseY < buttonY + buttonHeight / 2;
    }


    // =========================
    // PLAY BUTTON CLICK
    // =========================

    public boolean isPlayButtonClicked(PApplet app) {

        return isPlayButtonHovered(app);
    }


    // =========================
    // GAMEPLAY HUD
    // =========================

    public void drawScore(
            PApplet app,
            int score,
            int highScore,
            int level) {

        // HUD background
        app.fill(20, 20, 28);

        app.rect(
            0,
            0,
            app.width,
            45
        );


        // SCORE
        app.textAlign(
            PApplet.LEFT,
            PApplet.CENTER
        );

        app.textSize(17);

        app.fill(255);

        app.text(
            "SCORE: " + score,
            20,
            23
        );


        // HIGH SCORE
        app.text(
            "HIGH: " + highScore,
            150,
            23
        );


        // LEVEL
        app.text(
            "LEVEL: " + level,
            290,
            23
        );


        // PAUSE
        app.textAlign(
            PApplet.RIGHT,
            PApplet.CENTER
        );

        app.fill(180);

        app.text(
            "P = Pause",
            app.width - 20,
            23
        );
    }


    // =========================
    // GAME BOARD
    // =========================

    public void drawGameBoard(PApplet app) {

        app.fill(25, 25, 32);

        app.rect(
            0,
            45,
            app.width,
            app.height - 45
        );


        // Grid
        app.stroke(38, 38, 46);
        app.strokeWeight(1);

        for (int x = 0; x <= app.width; x += 15) {

            app.line(
                x,
                45,
                x,
                app.height
            );
        }

        for (int y = 45; y <= app.height; y += 15) {

            app.line(
                0,
                y,
                app.width,
                y
            );
        }


        // Border
        app.stroke(80, 80, 90);
        app.strokeWeight(2);

        app.noFill();

        app.rect(
            1,
            45,
            app.width - 2,
            app.height - 46
        );

        app.noStroke();
    }


    // =========================
    // PAUSE SCREEN
    // =========================

    public void drawPauseScreen(PApplet app) {

        app.textAlign(
            PApplet.CENTER,
            PApplet.CENTER
        );

        app.fill(0, 0, 0, 150);

        app.rect(
            0,
            45,
            app.width,
            app.height - 45
        );

        app.fill(255);
        app.textSize(40);

        app.text(
            "PAUSED",
            app.width / 2,
            app.height / 2 - 30
        );

        app.fill(200);
        app.textSize(20);

        app.text(
            "Press P to Resume",
            app.width / 2,
            app.height / 2 + 25
        );
    }


    // =========================
    // GAME OVER SCREEN
    // =========================

    public void drawGameOverScreen(
            PApplet app,
            int score,
            int highScore) {

        app.background(20, 20, 28);

        app.textAlign(
            PApplet.CENTER,
            PApplet.CENTER
        );


        // GAME OVER
        app.fill(255, 80, 80);
        app.textSize(48);

        app.text(
            "GAME OVER",
            app.width / 2,
            80
        );


        // SCORE CARD
        app.fill(30, 30, 40);

        app.rectMode(PApplet.CENTER);

        app.rect(
            app.width / 2,
            165,
            280,
            100,
            15
        );

        app.rectMode(PApplet.CORNER);


        // YOUR SCORE
        app.fill(200);
        app.textSize(16);

        app.text(
            "YOUR SCORE",
            app.width / 2,
            140
        );

        app.fill(255);
        app.textSize(32);

        app.text(
            score,
            app.width / 2,
            175
        );


        // HIGH SCORE
        app.fill(255, 220, 80);
        app.textSize(17);

        app.text(
            "HIGH SCORE: " + highScore,
            app.width / 2,
            220
        );


        // RESTART
        boolean restartHovering =
            isRestartButtonHovered(app);

        drawButton(
            app,
            app.width / 2,
            275,
            200,
            50,
            "RESTART",
            restartHovering
        );


        // MAIN MENU
        boolean menuHovering =
            isMenuButtonHovered(app);

        app.rectMode(PApplet.CENTER);

        if (menuHovering) {
            app.fill(80, 80, 90);
        } else {
            app.fill(55, 55, 65);
        }

        app.rect(
            app.width / 2,
            335,
            200,
            45,
            10
        );

        app.fill(255);
        app.textSize(18);

        app.text(
            "MAIN MENU",
            app.width / 2,
            335
        );

        app.rectMode(PApplet.CORNER);


        // Cursor
        if (restartHovering || menuHovering) {
            app.cursor(PApplet.HAND);
        } else {
            app.cursor(PApplet.ARROW);
        }
    }


    // =========================
    // RESTART BUTTON HOVER
    // =========================

    public boolean isRestartButtonHovered(PApplet app) {

        float buttonX = app.width / 2;
        float buttonY = 275;

        float buttonWidth = 200;
        float buttonHeight = 50;

        return app.mouseX > buttonX - buttonWidth / 2 &&
               app.mouseX < buttonX + buttonWidth / 2 &&
               app.mouseY > buttonY - buttonHeight / 2 &&
               app.mouseY < buttonY + buttonHeight / 2;
    }


    // =========================
    // MENU BUTTON HOVER
    // =========================

    public boolean isMenuButtonHovered(PApplet app) {

        float buttonX = app.width / 2;
        float buttonY = 335;

        float buttonWidth = 200;
        float buttonHeight = 45;

        return app.mouseX > buttonX - buttonWidth / 2 &&
               app.mouseX < buttonX + buttonWidth / 2 &&
               app.mouseY > buttonY - buttonHeight / 2 &&
               app.mouseY < buttonY + buttonHeight / 2;
    }


    // =========================
    // RESTART BUTTON CLICK
    // =========================

    public boolean isRestartButtonClicked(PApplet app) {

        return isRestartButtonHovered(app);
    }


    // =========================
    // MENU BUTTON CLICK
    // =========================

    public boolean isMenuButtonClicked(PApplet app) {

        return isMenuButtonHovered(app);
    }
}