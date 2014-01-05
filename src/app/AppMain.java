package app;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Michael
 */
public class AppMain extends Applet implements Runnable {

    public final static String GAME_NAME = "Inner Dialogue";
    public final static String FRM_TITLE = GAME_NAME + " [Michael \"KEFIR\" Miriti]";
    public static int frmWidth = 854;
    public static int frmHeight = 480;
    public static int pixelScale = 2;
    public static int msPassed = 0;
    public static Dimension displaySize;
    public static JFrame frmMain;
    public static boolean gameRunning;
    public static final int FRAMES_PER_SECOND = 60;
    public static AppMain app;
    private Color fillColor = new Color(10, 10, 10);
    public Image gameScreen;
    private Font fpsFont = new Font("Small Fonts", Font.PLAIN, 10);

    /**
     * Точка входа в приложение здесь
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Получаем разрешение экрана
        displaySize = Toolkit.getDefaultToolkit().getScreenSize();

        AppLog.log("Screen resolution: " + displaySize.width + "x" + displaySize.height);

        // Создаем оконце
        frmMain = new JFrame();

        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                processArg(args[i]);
            }
        }

        // Задаем заголовок оконца
        frmMain.setTitle(FRM_TITLE);
        // Делаем окошко фиксированного размера
        frmMain.setResizable(false);
        // Задаем размер окошка
        frmMain.setSize(new Dimension(frmWidth, frmHeight));
        System.out.println("Window " + frmHeight + "p");
        // Реакция на нажатие крестика - закрыть
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Размещаем окошко в центре экрана
        frmMain.setLocation((displaySize.width - frmMain.getWidth()) / 2, (displaySize.height - frmMain.getHeight()) / 2);

        // Создаем экземпляр приложения
        app = new AppMain();
        // Добавляем его на форму
        frmMain.add(app);

        // Показываем окно
        frmMain.setVisible(true);

        // Запускаем поток основной поток приложения
        app.start();
    }

    /**
     * Конструктор приложения
     */
    public AppMain() {
        addKeyListener(new AppKeyListener());
        setFocusable(true);
    }

    public static int getScreenWidth() {
        return frmWidth / pixelScale;
    }

    public static int getScreenHeight() {
        return frmHeight / pixelScale;
    }

    /**
     *
     * @param string
     */
    private static void processArg(String string) {
        switch (string) {
            case "-fullscreen":
                System.out.println("Fullscreen key set");
                // TODO make it later
                break;
            case "-480p":
                frmWidth = 854;
                frmHeight = 480;
                System.out.println("Running in 480p");
                break;
            case "-720p":
                frmWidth = 1280;
                frmHeight = 720;
                System.out.println("Running in 720p");
                break;
            case "-1080p":
                frmWidth = 1920;
                frmHeight = 1080;
                pixelScale = 3;
                System.out.println("Running in 1080p");
                break;
            default:
                System.out.println("Agrument \"" + string + "\" is unsupported!");
        }
    }

    public static boolean getBlinkState(int blinkLen) {
        return (msPassed / blinkLen) % 2 == 0;
    }

    /**
     * Обновление состояний
     *
     * @param deltaTime
     */
    public void update(int deltaTime) {
        StateCtrl.getCurrentState().update(deltaTime);
    }

    /**
     * Отрисовка
     */
    private void render() {
        Graphics g = gameScreen.getGraphics();
        g.setColor(fillColor);
        g.fillRect(0, 0, frmWidth / pixelScale, frmHeight / pixelScale);

        StateCtrl.getCurrentState().render(g);

        g = getGraphics();
        g.drawImage(gameScreen, 0, 0, frmWidth, frmHeight, null);
        g.dispose();
    }

    /**
     * Запуск потока
     */
    @Override
    public void start() {
        gameRunning = true;
        gameScreen = createGameScreen();
        StateCtrl.setState(StateCtrl.STATE_MENU);

        new Thread(this).start();
    }

    /**
     * Тело потока
     */
    @Override
    public void run() {
        long lastTime = new Date().getTime();
        long fpsTime = 0;
        int fpsCount = 0;
        int fpsLast = 0;

        while (gameRunning) {
            long currentTime = new Date().getTime();
            int deltaTime = (int) (currentTime - lastTime);
            lastTime = currentTime;

            if (fpsTime >= 1000) {
                fpsLast = fpsCount;
                fpsCount = 0;
                fpsTime = 0;
            } else {
                fpsTime += deltaTime;
            }

            update(deltaTime);
            render();

            msPassed += deltaTime;
            fpsCount++;
            try {
                Thread.sleep(1000 / FRAMES_PER_SECOND);
            } catch (InterruptedException ex) {
                Logger.getLogger(AppMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.exit(0);
    }

    /**
     * Создаем изображение в видеопамяти
     *
     * @return
     */
    private Image createGameScreen() {
        return createVolatileImage(frmWidth / pixelScale, frmHeight / pixelScale);
    }

    private void _drawFps(int fpsLast) {
        Graphics g = getGraphics();

        g.setFont(fpsFont);
        g.setColor(Color.red);
        g.drawString("fps: " + fpsLast, 0, 0);
        g.dispose();
    }
}
