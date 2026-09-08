import processing.core.PApplet;

public class GameUI {

    int backgroundColor = 30;
    int whiteColor = 255;

    // =========================
    // START SCREEN
    // =========================

    public void drawStartScreen(
            PApplet app,
            int highScore) {

        app.background(backgroundColor);

        app.textAlign(
            PApplet.CENTER,
            PApplet.CENTER
        );

        float centerX =
            app.width / 2.0f;

        float titleY =
            app.height * 0.19f;

        float subtitleY =
            app.height * 0.31f;

        float playButtonY =
            app.height * 0.46f;

        float controlsTitleY =
            app.height * 0.65f;

        float moveControlsY =
            app.height * 0.71f;

        float pauseControlsY =
            app.height * 0.77f;

        float highScoreY =
            app.height * 0.89f;

        app.fill(0, 255, 0);
        app.textSize(60);

        app.text(
            "SNAKE",
            centerX,
            titleY
        );

        app.fill(200);
        app.textSize(18);

        app.text(
            "Classic Snake Game",
            centerX,
            subtitleY
        );

        boolean hovering =
            isPlayButtonHovered(app);

        if (hovering) {
            app.cursor(PApplet.HAND);
        } else {
            app.cursor(PApplet.ARROW);
        }

        drawButton(
            app,
            centerX,
            playButtonY,
            200,
            55,
            "PLAY",
            hovering
        );

        app.fill(255);
        app.textSize(17);

        app.text(
            "CONTROLS",
            centerX,
            controlsTitleY
        );

        app.fill(180);
        app.textSize(15);

        app.text(
            "↑  ↓  ←  →   Move",
            centerX,
            moveControlsY
        );

        app.text(
            "P   Pause / Resume",
            centerX,
            pauseControlsY
        );

        app.fill(255, 220, 80);
        app.textSize(18);

        app.text(
            "HIGH SCORE: " + highScore,
            centerX,
            highScoreY
        );
    }

    // =========================
    // NORMAL BUTTON
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
    // PLAY BUTTON
    // =========================

    public boolean isPlayButtonHovered(
            PApplet app) {

        float buttonX =
            app.width / 2.0f;

        float buttonY =
            app.height * 0.46f;

        float buttonWidth = 200;
        float buttonHeight = 55;

        return app.mouseX >
                    buttonX - buttonWidth / 2 &&
               app.mouseX <
                    buttonX + buttonWidth / 2 &&
               app.mouseY >
                    buttonY - buttonHeight / 2 &&
               app.mouseY <
                    buttonY + buttonHeight / 2;
    }

    public boolean isPlayButtonClicked(
            PApplet app) {

        return isPlayButtonHovered(app);
    }

    // =========================
    // GAME HUD
    // =========================

    public void drawScore(
            PApplet app,
            int score,
            int highScore,
            int level) {

        app.fill(20, 20, 28);

        app.rect(
            0,
            0,
            app.width,
            GameConfig.HUD_HEIGHT
        );

        app.textAlign(
            PApplet.LEFT,
            PApplet.CENTER
        );

        app.textSize(17);

        app.fill(255);

        app.text(
            "SCORE: " + score,
            20,
            GameConfig.HUD_HEIGHT / 2
        );

        app.text(
            "HIGH: " + highScore,
            150,
            GameConfig.HUD_HEIGHT / 2
        );

        app.text(
            "LEVEL: " + level,
            290,
            GameConfig.HUD_HEIGHT / 2
        );

        app.textAlign(
            PApplet.RIGHT,
            PApplet.CENTER
        );

        app.fill(180);

        app.text(
            "P = Pause",
            app.width - 20,
            GameConfig.HUD_HEIGHT / 2
        );
    }

    // =========================
    // GAME BOARD
    // =========================

    public void drawGameBoard(PApplet app) {

        int boardBottom =
            app.height - GameConfig.CONTROL_HEIGHT;

        app.fill(25, 25, 32);

        app.rect(
            0,
            GameConfig.HUD_HEIGHT,
            app.width,
            boardBottom -
            GameConfig.HUD_HEIGHT
        );

        app.stroke(38, 38, 46);
        app.strokeWeight(1);

        for (
            int x = 0;
            x <= app.width;
            x += GameConfig.CELL_SIZE
        ) {

            app.line(
                x,
                GameConfig.HUD_HEIGHT,
                x,
                boardBottom
            );
        }

        for (
            int y = GameConfig.HUD_HEIGHT;
            y <= boardBottom;
            y += GameConfig.CELL_SIZE
        ) {

            app.line(
                0,
                y,
                app.width,
                y
            );
        }

        // Game border
        app.stroke(80, 80, 90);
        app.strokeWeight(2);
        app.noFill();

        app.rect(
            1,
            GameConfig.HUD_HEIGHT,
            app.width - 2,
            boardBottom -
            GameConfig.HUD_HEIGHT
        );

        app.noStroke();
    }

    // =========================
    // MOBILE D-PAD
    // =========================

    public void drawTouchControls(
            PApplet app) {

        int controlTop =
            app.height -
            GameConfig.CONTROL_HEIGHT;

        // Control background
        app.fill(35, 15, 15);

        app.rect(
            0,
            controlTop,
            app.width,
            GameConfig.CONTROL_HEIGHT
        );

        // Separator
        app.stroke(180, 50, 50);
        app.strokeWeight(2);

        app.line(
            0,
            controlTop,
            app.width,
            controlTop
        );

        app.noStroke();

        float centerX =
            app.width / 2.0f;

        float buttonSize = 55;

        float gap = 8;

        // D-pad positions
        float centerY =
            controlTop +
            GameConfig.CONTROL_HEIGHT * 0.62f;

        float upY =
            centerY - buttonSize - gap;

        float leftX =
            centerX - buttonSize - gap;

        float rightX =
            centerX + buttonSize + gap;

        float downY =
            centerY;

        // UP
        drawControlButton(
            app,
            centerX,
            upY,
            "UP"
        );

        // LEFT
        drawControlButton(
            app,
            leftX,
            centerY,
            "LEFT"
        );

        // DOWN
        drawControlButton(
            app,
            centerX,
            downY,
            "DOWN"
        );

        // RIGHT
        drawControlButton(
            app,
            rightX,
            centerY,
            "RIGHT"
        );
    }

    // =========================
    // D-PAD BUTTON
    // =========================

    private void drawControlButton(
            PApplet app,
            float x,
            float y,
            String label) {

        app.rectMode(PApplet.CENTER);

        // Dark red button
        app.fill(110, 25, 25);

        app.rect(
            x,
            y,
            55,
            55,
            10
        );

        // Red border
        app.stroke(255, 70, 70);
        app.strokeWeight(2);

        app.noFill();

        app.rect(
            x,
            y,
            55,
            55,
            10
        );

        app.noStroke();

        // Label
        app.fill(255);

        app.textAlign(
            PApplet.CENTER,
            PApplet.CENTER
        );

        app.textSize(12);

        app.text(
            label,
            x,
            y
        );

        app.rectMode(PApplet.CORNER);
    }

    // =========================
    // D-PAD DETECTION
    // =========================

    public int getControlButton(
            PApplet app,
            float x,
            float y) {

        int controlTop =
            app.height -
            GameConfig.CONTROL_HEIGHT;

        float centerX =
            app.width / 2.0f;

        float buttonSize = 55;

        float gap = 8;

        float centerY =
            controlTop +
            GameConfig.CONTROL_HEIGHT * 0.62f;

        float upY =
            centerY - buttonSize - gap;

        float leftX =
            centerX - buttonSize - gap;

        float rightX =
            centerX + buttonSize + gap;

        float downY =
            centerY;

        // LEFT
        if (isInsideButton(
                x,
                y,
                leftX,
                centerY,
                buttonSize)) {

            return 1;
        }

        // UP
        if (isInsideButton(
                x,
                y,
                centerX,
                upY,
                buttonSize)) {

            return 2;
        }

        // DOWN
        if (isInsideButton(
                x,
                y,
                centerX,
                downY,
                buttonSize)) {

            return 3;
        }

        // RIGHT
        if (isInsideButton(
                x,
                y,
                rightX,
                centerY,
                buttonSize)) {

            return 4;
        }

        return 0;
    }

    private boolean isInsideButton(
            float x,
            float y,
            float buttonX,
            float buttonY,
            float size) {

        return x >
                    buttonX - size / 2 &&
               x <
                    buttonX + size / 2 &&
               y >
                    buttonY - size / 2 &&
               y <
                    buttonY + size / 2;
    }

    // =========================
    // PAUSE SCREEN
    // =========================

    public void drawPauseScreen(
            PApplet app) {

        app.textAlign(
            PApplet.CENTER,
            PApplet.CENTER
        );

        app.fill(0, 0, 0, 150);

        app.rect(
            0,
            GameConfig.HUD_HEIGHT,
            app.width,
            app.height -
            GameConfig.HUD_HEIGHT -
            GameConfig.CONTROL_HEIGHT
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
    // LEVEL UP
    // =========================

    public void drawLevelUp(
            PApplet app,
            int level) {

        app.textAlign(
            PApplet.CENTER,
            PApplet.CENTER
        );

        app.fill(20, 20, 28, 220);

        app.rectMode(PApplet.CENTER);

        app.rect(
            app.width / 2,
            app.height / 2 - 70,
            220,
            75,
            15
        );

        app.rectMode(PApplet.CORNER);

        app.fill(0, 255, 120);
        app.textSize(24);

        app.text(
            "LEVEL UP!",
            app.width / 2,
            app.height / 2 - 85
        );

        app.fill(255);
        app.textSize(20);

        app.text(
            "LEVEL " + level,
            app.width / 2,
            app.height / 2 - 55
        );
    }

    // =========================
    // GAME OVER
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

        float centerX =
            app.width / 2.0f;

        float titleY =
            app.height * 0.20f;

        float scoreBoxY =
            app.height * 0.41f;

        float yourScoreY =
            app.height * 0.35f;

        float scoreValueY =
            app.height * 0.44f;

        float highScoreY =
            app.height * 0.55f;

        float restartButtonY =
            app.height * 0.69f;

        float menuButtonY =
            app.height * 0.84f;

        app.fill(255, 80, 80);
        app.textSize(48);

        app.text(
            "GAME OVER",
            centerX,
            titleY
        );

        app.fill(30, 30, 40);

        app.rectMode(PApplet.CENTER);

        app.rect(
            centerX,
            scoreBoxY,
            280,
            100,
            15
        );

        app.rectMode(PApplet.CORNER);

        app.fill(200);
        app.textSize(16);

        app.text(
            "YOUR SCORE",
            centerX,
            yourScoreY
        );

        app.fill(255);
        app.textSize(32);

        app.text(
            score,
            centerX,
            scoreValueY
        );

        app.fill(255, 220, 80);
        app.textSize(17);

        app.text(
            "HIGH SCORE: " + highScore,
            centerX,
            highScoreY
        );

        boolean restartHovering =
            isRestartButtonHovered(app);

        drawButton(
            app,
            centerX,
            restartButtonY,
            200,
            50,
            "RESTART",
            restartHovering
        );

        boolean menuHovering =
            isMenuButtonHovered(app);

        app.rectMode(PApplet.CENTER);

        if (menuHovering) {
            app.fill(80, 80, 90);
        } else {
            app.fill(55, 55, 65);
        }

        app.rect(
            centerX,
            menuButtonY,
            200,
            45,
            10
        );

        app.fill(255);
        app.textSize(18);

        app.text(
            "MAIN MENU",
            centerX,
            menuButtonY
        );

        app.rectMode(PApplet.CORNER);

        if (restartHovering || menuHovering) {
            app.cursor(PApplet.HAND);
        } else {
            app.cursor(PApplet.ARROW);
        }
    }

    // =========================
    // RESTART BUTTON
    // =========================

    public boolean isRestartButtonHovered(
            PApplet app) {

        float buttonX =
            app.width / 2.0f;

        float buttonY =
            app.height * 0.69f;

        float buttonWidth = 200;
        float buttonHeight = 50;

        return app.mouseX >
                    buttonX - buttonWidth / 2 &&
               app.mouseX <
                    buttonX + buttonWidth / 2 &&
               app.mouseY >
                    buttonY - buttonHeight / 2 &&
               app.mouseY <
                    buttonY + buttonHeight / 2;
    }

    public boolean isRestartButtonClicked(
            PApplet app) {

        return isRestartButtonHovered(app);
    }

    // =========================
    // MAIN MENU BUTTON
    // =========================

    public boolean isMenuButtonHovered(
            PApplet app) {

        float buttonX =
            app.width / 2.0f;

        float buttonY =
            app.height * 0.84f;

        float buttonWidth = 200;
        float buttonHeight = 45;

        return app.mouseX >
                    buttonX - buttonWidth / 2 &&
               app.mouseX <
                    buttonX + buttonWidth / 2 &&
               app.mouseY >
                    buttonY - buttonHeight / 2 &&
               app.mouseY <
                    buttonY + buttonHeight / 2;
    }

    public boolean isMenuButtonClicked(
            PApplet app) {

        return isMenuButtonHovered(app);
    }
}