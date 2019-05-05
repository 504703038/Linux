

 

import java.awt.AWTEvent;

import java.awt.AlphaComposite;

import java.awt.Color;

import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.Point;

import java.awt.RadialGradientPaint;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;

import java.awt.geom.Point2D;

import java.text.ParseException;

 

import javax.swing.ButtonGroup;

import javax.swing.JButton;

import javax.swing.JCheckBox;

import javax.swing.JComponent;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JLayer;

import javax.swing.JPanel;

import javax.swing.JRadioButton;

import javax.swing.JTextField;

import javax.swing.SwingUtilities;

import javax.swing.UIManager;

import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.WindowConstants;

import javax.swing.plaf.LayerUI;

import javax.swing.plaf.synth.SynthLookAndFeel;

 

/**

 * Use a spotlight layer as a JLayer to render a JFrame.

 * <p>

 * The default opaque style specified in this L&F is false.

 * 

 * @author Administrator

 */

public class JSpotlightLayer extends JPanel {

    private static final long serialVersionUID = -3416068588759196883L;

    private static JButton orderButton;

 

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            @Override

            public void run() {

                createAndShowGUI();

            }

        });

    }

 

    private static void createAndShowGUI() {

        initLookAndFeel();// specify the L&F

        LayerUI<JPanel> layerUI = new SpotlightLayerUI();

        JPanel contentPane = new JSpotlightLayer();

        JLayer<JPanel> jlayer = new JLayer<>(contentPane, layerUI);

        final JFrame f = new JFrame("SpotlightLayerUI");

        f.add(jlayer);

        f.getRootPane().setDefaultButton(orderButton);

        f.setSize(270, 185);

        f.setResizable(false);

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        f.setLocationRelativeTo(null);

        f.setVisible(true);

    }

 

    private static void initLookAndFeel() {

        SynthLookAndFeel lookAndFeel = new SynthLookAndFeel();

        try {

            lookAndFeel.load(JSpotlightLayer.class

                    .getResourceAsStream("lnfimpl/synth.xml"),

                    JSpotlightLayer.class);

        } catch (ParseException e) {

            System.err.println("There is an error in parsing xml of Synth");

            System.err.println("Using the default look and feel");

            e.printStackTrace();

        }

        try {

            UIManager.setLookAndFeel(lookAndFeel);

        } catch (UnsupportedLookAndFeelException e) {

            System.err.println("Synth is not a supported look and feel");

            System.err.println("Using the default look and feel");

            e.printStackTrace();

        }

    }

 

    private static class SpotlightLayerUI extends LayerUI<JPanel> {

        private static final long serialVersionUID = -2815331572730280489L;

        private boolean mActive;

        private int mX, mY;

 

        @Override

        public void installUI(JComponent c) {

            super.installUI(c);

            @SuppressWarnings("unchecked")

            JLayer<JPanel> jlayer = (JLayer<JPanel>) c;

            jlayer.setLayerEventMask(AWTEvent.MOUSE_EVENT_MASK

                    | AWTEvent.MOUSE_MOTION_EVENT_MASK);

        }

 

        @Override

        public void uninstallUI(JComponent c) {

            super.uninstallUI(c);

            @SuppressWarnings("unchecked")

            JLayer<JPanel> jlayer = (JLayer<JPanel>) c;

            jlayer.setLayerEventMask(0);

        }

 

        @Override

        public void paint(Graphics g, JComponent c) {

            super.paint(g, c);

            Graphics2D g2 = (Graphics2D) g.create();

            if (mActive) {

                /* Create a radial gradient, transparent in the middle */

                Point2D center = new Point2D.Float(mX, mY);

                float radius = 72;

                float[] dist = { 0.0f, 1.0f };

                Color[] colors = { new Color(0.0f, 0.0f, 0.0f, 0.0f),

                        Color.BLACK };

                RadialGradientPaint p = new RadialGradientPaint(center, radius,

                        dist, colors);

                g2.setPaint(p);

                g2.setComposite(AlphaComposite.getInstance(

                        AlphaComposite.SRC_OVER, .6f));

                g2.fillRect(0, 0, c.getWidth(), c.getHeight());

            }

            g2.dispose();

        }

 

        @Override

        protected void processMouseEvent(MouseEvent e,

                JLayer<? extends JPanel> l) {

            if (e.getID() == MouseEvent.MOUSE_ENTERED) {

                mActive = true;

            } else if (e.getID() == MouseEvent.MOUSE_EXITED) {

                mActive = false;

            }

            l.repaint();

        }

 

        @Override

        protected void processMouseMotionEvent(MouseEvent e,

                JLayer<? extends JPanel> l) {

            Point p = SwingUtilities.convertPoint(e.getComponent(),

                    e.getPoint(), l);

            mX = p.x;

            mY = p.y;

            l.repaint();

        }

    }

 

    JSpotlightLayer() {

        ButtonGroup entreeGroup = new ButtonGroup();

        JRadioButton radioButton = new JRadioButton("Beef", true);

        JRadioButton radioButton2 = new JRadioButton("Chicken");

        JRadioButton radioButton3 = new JRadioButton("Vegetable");

        entreeGroup.add(radioButton);

        entreeGroup.add(radioButton2);

        entreeGroup.add(radioButton3);

        JCheckBox jCheckBox = new JCheckBox("Ketchup");

        JCheckBox jCheckBox2 = new JCheckBox("Mustard");

        JCheckBox jCheckBox3 = new JCheckBox("Pickles");

        JLabel label = new JLabel("Special requests:");

        JTextField tf = new JTextField(15);

        orderButton = new JButton("Place Order");

        orderButton.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                System.out.println("default button clicked");

                System.exit(0);// normal termination of JVM

            }

        });

        JButton cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                System.out.println("cancel button clicked");

                System.exit(0);// normal termination of JVM

            }

        });

        add(radioButton);

        add(radioButton2);

        add(radioButton3);

        add(jCheckBox);

        add(jCheckBox2);

        add(jCheckBox3);

        add(label);

        add(tf);

        add(orderButton);

        add(cancelButton);

    }

}