package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * @author Software Engineering teachers
 */
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.AdminUser;
import domain.Event;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

public class MainAdminGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonCreateQuestion = null;
	private JButton jButtonCreateEvent = null;

	private static BLFacade appFacadeInterface = LoginGUI.getBusinessLogic();

	protected JLabel jLabelSelectOption;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton jButtonCreateForecast;
	private JButton buttonAdmin;
	private AdminUser userlog = null;
	private JButton buttonCloseEvent;
	private JButton jButtonQueryQueries;

	private JLabel lblFotoCrearEvento;
	private JLabel lblFotoCrearQuestion;
	private JLabel lblFotoCrearForecast;
	private JLabel lblFotoSeeQuestions;
	private JLabel lblFotoAdministration;
	private JLabel lblFotoCloseEvent;


	/**
	 * This is the default constructor
	 */
	public MainAdminGUI(AdminUser au) {
		super();

		userlog = au;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					// if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println(
							"Error: " + e1.toString() + " , probably problems with Business Logic or Database");
				}
				// System.exit(1);
			}
		});

		initialize();
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.setSize(630, 421);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));

	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setBackground(Color.WHITE);
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton3());
			jContentPane.add(getBoton2());
			jContentPane.add(getJButtonCreateForecast());
			jContentPane.add(getPanel());
			jContentPane.add(getJButtonAdministration());
			jContentPane.add(getbuttonCloseEvent());

			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lblFotoSeeQuestions.setVisible(true);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lblFotoSeeQuestions.setVisible(false);

				}
			});
			jButtonQueryQueries.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame a = new FindQuestionsGUI(userlog);
					a.setAlwaysOnTop(true);
					a.setVisible(true);
					dispose();
				}
			});
			jButtonQueryQueries.setText(
					ResourceBundle.getBundle("Etiquetas").getString("MainAdminGUI.jButtonQueryQuestions.text")); //$NON-NLS-1$ //$NON-NLS-2$
			jButtonQueryQueries.setBounds(34, 191, 267, 38);
			jContentPane.add(jButtonQueryQueries);

			ImageIcon icon = new ImageIcon("imagenes/fotoCrearEvento.png");
			lblFotoCrearEvento = new JLabel(icon);
			lblFotoCrearEvento.setBounds(320, 108, 265, 192);
			lblFotoCrearEvento.setVisible(false);
			jContentPane.add(lblFotoCrearEvento);
			
			
			
			ImageIcon icon2 = new ImageIcon("imagenes/fotoCrearQuestion.png");
			lblFotoCrearQuestion = new JLabel(icon2);
			lblFotoCrearQuestion.setBounds(320, 108, 265, 192);
			lblFotoCrearQuestion.setVisible(false);
			jContentPane.add(lblFotoCrearQuestion);
			
			
			ImageIcon icon3 = new ImageIcon("imagenes/fotoCrearForecast.png");
			lblFotoCrearForecast = new JLabel(icon3);
			lblFotoCrearForecast.setBounds(320, 108, 265, 192);
			lblFotoCrearForecast.setVisible(false);
			jContentPane.add(lblFotoCrearForecast);
			
			
			
			ImageIcon icon4 = new ImageIcon("imagenes/fotoSeeQuestions.png");
			lblFotoSeeQuestions = new JLabel(icon4);
			lblFotoSeeQuestions.setBounds(320, 108, 265, 192);
			lblFotoSeeQuestions.setVisible(false);
			jContentPane.add(lblFotoSeeQuestions);
			
			
			
			ImageIcon icon5 = new ImageIcon("imagenes/fotoAdministration.png");
			lblFotoAdministration = new JLabel(icon5);
			lblFotoAdministration.setBounds(320, 108, 265, 192);
			lblFotoAdministration.setVisible(false);
			jContentPane.add(lblFotoAdministration);
			
			
			ImageIcon icon6 = new ImageIcon("imagenes/fotoCloseEvent.png");
			lblFotoCloseEvent = new JLabel(icon6);
			lblFotoCloseEvent.setBounds(320, 108, 265, 192);
			lblFotoCloseEvent.setVisible(false);
			jContentPane.add(lblFotoCloseEvent);
			
			
			

		}
		return jContentPane;
	}

	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (jButtonCreateQuestion == null) {
			jButtonCreateQuestion = new JButton();
			jButtonCreateQuestion.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lblFotoCrearQuestion.setVisible(true);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lblFotoCrearQuestion.setVisible(false);

				}
			});
			jButtonCreateQuestion.setBounds(34, 108, 267, 38);
			jButtonCreateQuestion.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestion"));
			jButtonCreateQuestion.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new CreateQuestionGUI(new Vector<Event>(), userlog);
					a.setAlwaysOnTop(true);
					a.setVisible(true);
					close();
				}
			});
		}
		return jButtonCreateQuestion;
	}

	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonCreateEvent == null) {
			jButtonCreateEvent = new JButton();
			jButtonCreateEvent.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					lblFotoCrearEvento.setVisible(true);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lblFotoCrearEvento.setVisible(false);

				}
			});
			jButtonCreateEvent.setBounds(34, 67, 267, 38);
			jButtonCreateEvent.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
			jButtonCreateEvent.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new CreateEventGUI(userlog);
					a.setAlwaysOnTop(true);
					a.setVisible(true);
					a.setVisible(true);
					dispose();
				}
			});
		}
		return jButtonCreateEvent;
	}

	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setBounds(39, 8, 262, 76);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);

		}
		return jLabelSelectOption;
	}

	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.setBackground(Color.WHITE);
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}

	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton_1.setBackground(Color.WHITE);
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}

	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton_2.setBackground(Color.WHITE);
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_2);
		}
		return rdbtnNewRadioButton_2;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBounds(38, 318, 262, 38);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}

	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonCreateEvent.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
		jButtonCreateQuestion.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestion"));
		jButtonCreateForecast
				.setText(ResourceBundle.getBundle("Etiquetas").getString("MainAdminGUI.jButtonCreateForecast.text"));
		jButtonQueryQueries
				.setText(ResourceBundle.getBundle("Etiquetas").getString("MainAdminGUI.jButtonQueryQuestions.text"));
		buttonAdmin.setText(ResourceBundle.getBundle("Etiquetas").getString("MainAdminGUI.buttonAdmin.text"));
		buttonCloseEvent.setText(ResourceBundle.getBundle("Etiquetas").getString("MainAdminGUI.buttonCloseEvent.text"));

		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	public void close() {
		this.setVisible(false);
	}

	private JButton getJButtonCreateForecast() {
		if (jButtonCreateForecast == null) {
			jButtonCreateForecast = new JButton();
			jButtonCreateForecast.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lblFotoCrearForecast.setVisible(true);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lblFotoCrearForecast.setVisible(false);

				}
			});
			jButtonCreateForecast.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame a = new CreateForecastGUI(userlog);
					a.setAlwaysOnTop(true);
					a.setVisible(true);
					close();
				}
			});
			jButtonCreateForecast.setBounds(34, 150, 267, 38);
			jButtonCreateForecast.setText(
					ResourceBundle.getBundle("Etiquetas").getString("MainAdminGUI.jButtonCreateForecast.text")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return jButtonCreateForecast;
	}

	private JButton getJButtonAdministration() {
		if (buttonAdmin == null) {
			buttonAdmin = new JButton();
			buttonAdmin.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lblFotoAdministration.setVisible(true);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lblFotoAdministration.setVisible(false);

				}
			});
			buttonAdmin.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame a = new AdministrationGUI(userlog);
					a.setAlwaysOnTop(true);
					a.setVisible(true);
					close();
				}
			});
			buttonAdmin.setBounds(34, 232, 267, 38);
			buttonAdmin.setText(ResourceBundle.getBundle("Etiquetas").getString("MainAdminGUI.buttonAdmin.text")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return buttonAdmin;
	}

	private JButton getbuttonCloseEvent() {
		if (buttonCloseEvent == null) {
			buttonCloseEvent = new JButton();
			buttonCloseEvent.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lblFotoCloseEvent.setVisible(true);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lblFotoCloseEvent.setVisible(false);

				}
			});
			buttonCloseEvent.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JFrame a = new CloseEventGUI(userlog);
					a.setAlwaysOnTop(true);
					a.setVisible(true);
					dispose();
				}
			});
			buttonCloseEvent.setBounds(34, 273, 267, 38);
			buttonCloseEvent
					.setText(ResourceBundle.getBundle("Etiquetas").getString("MainAdminGUI.buttonCloseEvent.text")); //$NON-NLS-1$ //$NON-NLS-2$
			jContentPane.add(buttonCloseEvent);

		}
		return buttonCloseEvent;
	}
} // @jve:decl-index=0:visual-constraint="0,0"
