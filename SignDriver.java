import javax.swing.*;

/* Menu machinery provided, but you are free
 * to ignore it if your solution does not involve menus
 */
public class SignDriver {

	public static void main(String[] args) {
		DisplayWindow d = new DisplayWindow();
		JMenuBar menuBar = new JMenuBar(); // make menu bar
		d.setJMenuBar(menuBar); // add menu bar to window d
		MovingSignPanel p = new MovingSignPanel(menuBar);
		d.addPanel(p);
		d.showFrame();
	}
}