import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.awt.geom.Ellipse2D;

public class PortfolioApp extends JFrame {

    // Declare components
    JLabel nameLabel, welcomeLabel, aboutLabel, skillsLabel, projectLabel, educationLabel, contactLabel, thankYouLabel, logoLabel,logoLabel1;
    JLabel linkedinLabel, instagramLabel, youtubeLabel, educationIconLabel,myImageIconLabel, certificateLabel,bottomlogoLabel,linkedinLabel1, instagramLabel1, youtubeLabel1;
    JTextArea aboutArea, skillsArea, projectArea, educationArea, introArea,messageField;
    JButton contactMeButton, topButton,submitButton;
    JScrollPane aboutScrollPane, skillsScrollPane, projectScrollPane, educationScrollPane, certificateScrollPane;
    private String welcomeTextPart1 = "Welcome to My World : ";  // First part in one color
    private String welcomeTextPart2 = "Igniting the Spark of Innovation and Engineering ";  // Second part in another color
    private CustomPanel mainPanel;
    private Timer timer;
    private int charInd = 0;;
    JTable certificateTable;
    JScrollPane mainScrollPane;
    JTextField nameField,emailField;

    public PortfolioApp() {
        // Set up the frame
        this.setTitle("Portfolio - Dharaneesh Guhan");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);

        // Top bar (contains hamburger menu, name, and logo)
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Color.WHITE);
        topBar.setPreferredSize(new Dimension(this.getWidth(), 80));

        // Left panel for name and logo
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(Color.WHITE);

        // Load and resize the logo image
        ImageIcon logoIcon = new ImageIcon("d:\\Quick Share\\logo.jpg"); // Replace with the actual image path
        if (logoIcon.getIconWidth() != -1) {
            Image resizedLogo = logoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            logoLabel = new JLabel(new ImageIcon(resizedLogo));
            leftPanel.add(logoLabel);
        }

        // Name label next to logo
        nameLabel = new JLabel("Dharaneesh Guhan", JLabel.LEFT);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        leftPanel.add(nameLabel);

        // Right panel for hamburger menu
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setBackground(Color.WHITE);

        // Hamburger menu button
        JButton hamburgerMenu = new JButton("≡");
        hamburgerMenu.setFont(new Font("Arial", Font.BOLD, 36));
        hamburgerMenu.setFocusPainted(false);
        hamburgerMenu.setBackground(Color.WHITE);
        hamburgerMenu.setBorderPainted(false);
        hamburgerMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Create a pop-up menu for the hamburger menu
        JPopupMenu menu = new JPopupMenu();
        JMenuItem aboutMenuItem = new JMenuItem("About Me");
        JMenuItem skillsMenuItem = new JMenuItem("Skills");
        JMenuItem projectMenuItem = new JMenuItem("Projects");
        JMenuItem educationMenuItem = new JMenuItem("Education");
        JMenuItem certificateMenuItem = new JMenuItem("Certificates");
        JMenuItem contactMenuItem = new JMenuItem("Contact");

        // Add menu items to the pop-up menu
        menu.add(aboutMenuItem);
        menu.add(skillsMenuItem);
        menu.add(projectMenuItem);
        menu.add(educationMenuItem);
        menu.add(certificateMenuItem);
        menu.add(contactMenuItem);

        // Show the menu when hamburger button is clicked
        hamburgerMenu.addActionListener(e -> menu.show(hamburgerMenu, hamburgerMenu.getWidth(), hamburgerMenu.getHeight()));


         // Action listeners for navigation
         aboutMenuItem.addActionListener(e -> scrollToSection(0)); // Scroll to about section
         skillsMenuItem.addActionListener(e -> scrollToSection(6)); // Scroll to skills section
         projectMenuItem.addActionListener(e -> scrollToSection(8)); // Scroll to projects section
         educationMenuItem.addActionListener(e -> scrollToSection(10)); // Scroll to education section
         certificateMenuItem.addActionListener(e -> scrollToSection(12)); // Scroll to certificates section
         contactMenuItem.addActionListener(e -> scrollToSection(14)); // Scroll to contact section
 
         // Show the menu when hamburger button is clicked
         hamburgerMenu.addActionListener(e -> menu.show(hamburgerMenu, hamburgerMenu.getWidth(), hamburgerMenu.getHeight()));
         rightPanel.add(hamburgerMenu);
 
         // Add leftPanel (logo and name) and rightPanel (hamburger) to top bar
         topBar.add(leftPanel, BorderLayout.WEST);
         topBar.add(rightPanel, BorderLayout.EAST);
 
         // Add top bar to the frame
         this.add(topBar, BorderLayout.NORTH);

         mainPanel = new CustomPanel();
         mainPanel.setLayout(new GridBagLayout());
         mainPanel.setOpaque(true); // Ensure it's opaque
         mainPanel.setBackground(Color.WHITE); // Set background to white
         
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.insets = new Insets(10, 10, 10, 10);
         gbc.fill = GridBagConstraints.BOTH;
         gbc.weightx = 1;

        // Initialize welcomeLabel as empty with CENTER alignment
        welcomeLabel = new JLabel("", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 36));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.1;
        mainPanel.add(welcomeLabel, gbc);

        // Timer for typing animation
        timer = new Timer(100, new ActionListener() {  // Delay of 100ms between letters
            @Override
            public void actionPerformed(ActionEvent e) {
                if (charInd < welcomeTextPart1.length()) {
                    // Keep typing the first part of the text in blue
                    welcomeLabel.setText("<html><span style='color: black;'>" + welcomeTextPart1.substring(0, charInd) + "</span></html>");
                    charInd++;
                } else if (charInd < welcomeTextPart1.length() + welcomeTextPart2.length()) {
                    // After first part, start typing the second part in green
                    int secondPartIndex = charInd - welcomeTextPart1.length();
                    welcomeLabel.setText("<html><span style='color: black;'>" + welcomeTextPart1 + "</span>"
                        + "<span style='color: #FFD700;'>" + welcomeTextPart2.substring(0, secondPartIndex) + "</span></html>");
                    charInd++;
                } else {
                    // Stop the timer once both parts are fully displayed
                    timer.stop();
                }
            }
        });
        timer.start();

        //intro section
        introArea = new JTextArea("I'm Dharaneesh Guhan, a passionate and versatile coder with expertise in C, C++, and Java, coupled with a solid foundation in app and webpage development. I specialize in crafting efficient, scalable, and user-friendly solutions. My approach combines technical precision with a keen eye for design, ensuring both functionality and an engaging user experience. Constantly exploring new technologies, I aim to stay at the forefront of innovation, delivering creative and robust solutions across platforms.\"");
        introArea.setLineWrap(true);
        introArea.setWrapStyleWord(true);
        introArea.setEditable(false);
        introArea.setBorder(null);

        introArea.setOpaque(false);
        gbc.gridy = 1;
        mainPanel.add(introArea, gbc);

        // Create LinkedIn, Instagram, and YouTube labels with icons
        linkedinLabel = new JLabel("LinkedIn", JLabel.CENTER);
        instagramLabel = new JLabel("Instagram", JLabel.CENTER);
        youtubeLabel = new JLabel("YouTube", JLabel.CENTER);

        // Set icons and adjust text position (below the icon)
        linkedinLabel.setIcon(resizeIcon(new ImageIcon("d:\\Quick Share\\Linkedin.jpg"), 50, 50)); // Replace with actual paths
        instagramLabel.setIcon(resizeIcon(new ImageIcon("d:\\Quick Share\\instagram.jpg"), 50, 50));
        youtubeLabel.setIcon(resizeIcon(new ImageIcon("d:\\Quick Share\\youtube.jpg"), 50, 50));

        // Set text to appear below the icon
        linkedinLabel.setHorizontalTextPosition(JLabel.CENTER);
        linkedinLabel.setVerticalTextPosition(JLabel.BOTTOM);
        instagramLabel.setHorizontalTextPosition(JLabel.CENTER);
        instagramLabel.setVerticalTextPosition(JLabel.BOTTOM);
        youtubeLabel.setHorizontalTextPosition(JLabel.CENTER);
        youtubeLabel.setVerticalTextPosition(JLabel.BOTTOM);

        // Set cursor to hand for interactivity
        linkedinLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        instagramLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        youtubeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add click events to labels
        linkedinLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openLink("https://www.linkedin.com/in/dharaneeshguhan-t-a36140284");
            }
        });

        instagramLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openLink("https://www.instagram.com/ig_dharaneeshguhan");
            }
        });

        youtubeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openLink("https://youtube.com/@ihtamil-dg");
            }
        });

        // Social Media Panel
        CustomPanel socialMediaPanel = new CustomPanel();
        socialMediaPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        socialMediaPanel.add(instagramLabel);
        socialMediaPanel.add(youtubeLabel);
        socialMediaPanel.add(linkedinLabel);
        gbc.gridy = 2;
        mainPanel.add(socialMediaPanel, gbc);

        mainScrollPane = new JScrollPane(mainPanel);
        this.add(mainScrollPane, BorderLayout.CENTER);

        // Create a panel for the Contact Me button
        CustomPanel contactButtonPanel = new CustomPanel();
        contactButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        contactButtonPanel.setPreferredSize(new Dimension(this.getWidth() / 2, 60)); // Set preferred size to half of the window width

        // Contact Me Button
        contactMeButton = new JButton("Contact Me  -> ");
        contactMeButton.setFont(new Font("Arial", Font.PLAIN, 18));
        contactMeButton.setForeground(Color.WHITE);
        contactMeButton.setBackground(Color.decode("#1E1D43"));
        contactMeButton.setPreferredSize(new Dimension(200, 50)); // Width of the button
        contactButtonPanel.add(contactMeButton); // Add button to the panel

        // Add the contact button panel to the main panel
        gbc.gridy = 3;
        gbc.gridwidth = 1; // Reset grid width
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        mainPanel.add(contactButtonPanel, gbc);

        // Add action listener to scroll to the contact section
        contactMeButton.addActionListener(e -> {
            // Scroll to the contact section
            JScrollBar vertical = mainScrollPane.getVerticalScrollBar();
            vertical.setValue( vertical.getMaximum() ); // Scroll to the bottom
        });

        // Create the About Me panel
        JPanel aboutPanel = new JPanel(new GridBagLayout());
        aboutPanel.setBackground(Color.decode("#F5F5F5"));  // Light background color

        // Create a new GridBagConstraints instance for the aboutPanel
        GridBagConstraints gbcAbout = new GridBagConstraints();
        gbcAbout.anchor = GridBagConstraints.WEST;  // Ensure components are aligned to the left
        gbcAbout.fill = GridBagConstraints.NONE;
        gbcAbout.insets = new Insets(10, 10, 10, 10);  // Add some padding around components

        // Adding the Image
        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon("d:\\Quick Share\\myimage.jpg"); // Replace with the path of your image
        Image img = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));
        gbcAbout.gridx = 0;
        gbcAbout.gridy = 0;
        gbcAbout.gridheight = 4;  // Ensure the image takes up the entire left side, adjust gridheight
        gbcAbout.insets = new Insets(10, 10, 10, 20);  // Add padding around the image
        aboutPanel.add(imageLabel, gbcAbout);

        // Adding the text part
        gbcAbout.gridheight = 1;  // Reset grid height for the text components
        gbcAbout.gridx = 1;  // Move to the right of the image
        gbcAbout.gridy = 0;  // Start at the top

        // THIS IS ME label
        JLabel thisIsMeLabel = new JLabel("THIS IS ME");
        thisIsMeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        thisIsMeLabel.setForeground(Color.decode("#646464"));  // Grey color
        gbcAbout.insets = new Insets(5, 10, 5, 10);  // Smaller padding between text elements
        aboutPanel.add(thisIsMeLabel, gbcAbout);

        // DHARANEESH GUHAN label (your name)
        gbcAbout.gridy++;  // Move down by 1 row
        JLabel nameLabel = new JLabel("DHARANEESH GUHAN");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 36));
        nameLabel.setForeground(Color.decode("#000000"));  // Black color
        gbcAbout.insets = new Insets(0, 10, 5, 10);  // Reduce gap between THIS IS ME and name
        aboutPanel.add(nameLabel, gbcAbout);

        // Description label
        gbcAbout.gridy++;  // Move down by 1 row
        JLabel descriptionLabel = new JLabel("<html>You will begin to realize why this exercise is called the Dickens Pattern (with reference to the ghost showing Scrooge some different future)</html>");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionLabel.setForeground(Color.decode("#646464"));  // Grey color
        gbcAbout.insets = new Insets(0, 10, 10, 10);  // Control spacing between name and description
        aboutPanel.add(descriptionLabel, gbcAbout);

        // My Certificate Button
        gbcAbout.gridy++;  // Move down by 1 row
        gbcAbout.gridx = 1;  // Center the button horizontally
        gbcAbout.anchor = GridBagConstraints.CENTER;  // Center-align the button
        JButton certificateButton = new JButton("My Certificate");
        certificateButton.setBackground(Color.decode("#2A85FF"));  // Button background color
        certificateButton.setForeground(Color.WHITE);  // Button text color
        certificateButton.setFont(new Font("Arial", Font.BOLD, 14));
        certificateButton.setPreferredSize(new Dimension(150, 40));  // Set button size
        aboutPanel.add(certificateButton, gbcAbout);

        // View My Projects Button
        gbcAbout.gridy++;  // Move down by 1 row for the second button
        JButton projectsButton = new JButton("View My Projects");
        projectsButton.setBackground(Color.decode("#2A85FF"));  // Button background color
        projectsButton.setForeground(Color.WHITE);  // Button text color
        projectsButton.setFont(new Font("Arial", Font.BOLD, 14));
        projectsButton.setPreferredSize(new Dimension(150, 40));  // Set button size
        aboutPanel.setBackground(Color.WHITE);
        aboutPanel.add(projectsButton, gbcAbout);
        // Add action listener to the My Certificate button
        certificateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle opening of certificate or any other action
                JOptionPane.showMessageDialog(null, "Opening Certificate...");
            }
        });

        // Add action listener to the View My Projects button
        projectsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Scroll to the projects section
                JScrollBar verticalScrollBar = mainScrollPane.getVerticalScrollBar();
                verticalScrollBar.setValue(775);  // Scroll to the position
            }
        });

        // Now, add the aboutPanel to the mainPanel
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.gridx = 0;
        gbcMain.gridy = 4;
        gbcMain.anchor = GridBagConstraints.NORTHWEST;  // Align to the top-left of the page
        gbcMain.insets = new Insets(0, 0, 0, 0);  // No additional padding for the main panel

        // Add the aboutPanel to the mainPanel
        mainPanel.add(aboutPanel, gbcMain);


        // Create a new panel for the skills section
        JPanel skillsPanel = new JPanel(new GridBagLayout());
        skillsPanel.setBackground(Color.decode("#1E1D43"));  // Set the background color to match your theme
        skillsPanel.setBackground(Color.decode("#1E1D43"));  // Set the background color to match your theme
        
        // Skill section title
        JLabel skillsLabel = new JLabel("Skills:");
        skillsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.insets = new Insets(5, 0, 5, 0);
        skillsLabel.setForeground(Color.decode("#FFC900"));  // Setting the text color to match the image
        gbc.gridy = 0;  // Reset Y position for the skills section
        skillsPanel.add(skillsLabel, gbc);  // Add the label to the skillsPanel
        
        // Featured Skills Section
                JLabel featuredLabel = new JLabel("Featured:");
                featuredLabel.setFont(new Font("Arial", Font.BOLD, 16));
                featuredLabel.setForeground(Color.WHITE);
                gbc.gridy++;
                skillsPanel.add(featuredLabel, gbc);
        
                JPanel featuredPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
                featuredPanel.setBackground(Color.decode("#1E1D43"));
                addSkillsWithIcons(featuredPanel, new String[]{"CSS", "HTML", "JavaScript", "Java", "C","C++"},
                        new String[]{"css.png", "html.png", "javascript.png", "java.png", "C.png","C++.jpn"});
                gbc.gridy++;
                skillsPanel.add(featuredPanel, gbc);
        
                // Languages Skills Section
                JLabel languagesLabel = new JLabel("Languages:");
                languagesLabel.setFont(new Font("Arial", Font.BOLD, 16));
                languagesLabel.setForeground(Color.WHITE);
                gbc.gridy++;
                skillsPanel.add(languagesLabel, gbc);
        
                JPanel languagesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
                languagesPanel.setBackground(Color.decode("#1E1D43"));
                addSkillsWithIcons(languagesPanel, new String[]{"CSS", "HTML", "JavaScript", "Java", "C","C++"},
                        new String[]{"css.png", "html.png", "javascript.png", "java.png", "C.png","C++.jpn"});
                gbc.gridy++;
                skillsPanel.add(languagesPanel, gbc);
        
                // Frontend Skills Section
                JLabel frontendLabel = new JLabel("Frontend:");
                frontendLabel.setFont(new Font("Arial", Font.BOLD, 16));
                frontendLabel.setForeground(Color.WHITE);
                gbc.gridy++;
                skillsPanel.add(frontendLabel, gbc);
        
                JPanel frontendPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
                frontendPanel.setBackground(Color.decode("#1E1D43"));
                addSkillsWithIcons(frontendPanel, new String[]{"Astro", "Google Maps", "CSS", "HTML", "JavaScript", "Java", "C","C++"},
                        new String[]{"astro.jpg", "googlemaps.png", "css.png", "html.png", "javascript.png", "java.png", "C.png","C++.jpn"});
                gbc.gridy++;
                skillsPanel.add(frontendPanel, gbc);
        
                // Backend Skills Section
                JLabel backendLabel = new JLabel("Backend:");
                backendLabel.setFont(new Font("Arial", Font.BOLD, 16));
                backendLabel.setForeground(Color.WHITE);
                gbc.gridy++;
                skillsPanel.add(backendLabel, gbc);
        
                JPanel backendPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
                backendPanel.setBackground(Color.decode("#1E1D43"));
                addSkillsWithIcons(backendPanel, new String[]{"JavaScript", "Java","SQL"},
                        new String[]{"javascript.png", "java.png","SQL.png"});
                gbc.gridy++;
                skillsPanel.add(backendPanel, gbc);
        
                // Add the skillsPanel to the main panel
                gbc.gridy++;
                mainPanel.add(skillsPanel, gbc);

        // Project section title
        JLabel projectTitleLabel = new JLabel("Projects :");
        projectTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));  // Set font and size for the title
        projectTitleLabel.setForeground(Color.decode("#000000"));  // Black text color
        gbc.gridx = 0;  // Set X position
        gbc.gridy = 10;  // Set Y position for this section
        mainPanel.add(projectTitleLabel, gbc);  // Add project title to the panel

        // Subtitle for the project section
        JLabel projectSubtitleLabel = new JLabel("Innovations in Technology and Leadership");
        projectSubtitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));  // Set font and size for the subtitle
        projectSubtitleLabel.setForeground(Color.decode("#808080"));  // Light gray color for the subtitle
        gbc.gridy++;  // Move to the next row
        mainPanel.add(projectSubtitleLabel, gbc);  // Add project subtitle

        // Create a reusable method to add project containers
        JPanel nearTreatPanel = createProjectContainer(
            "SafeMate", 
            "SafeMate is a human safety app that enables secure, peer-to-peer communication using ultrasonic waves, Bluetooth, and Wi-Fi. It allows users to send distress signals and share real-time locations without relying on traditional networks, ensuring safety in emergencies, even without connectivity.", 
            "Project Link"
        );
        gbc.gridy++;  // Move to the next row
        mainPanel.add(nearTreatPanel, gbc);

        // Add more project containers as needed
        JPanel anotherProjectPanel = createProjectContainer(
            "SerenityMeter", 
            "This app helps users monitor their mental health through AI-driven insights and personalized recommendations. It tracks mood, sleep, and physical activity, offering real-time feedback and intervention tools to improve well-being. Suitable for individuals seeking mental health support and professionals looking to enhance client care.", 
            "Project Link"
        );
        gbc.gridy++;  // Move to the next row
        mainPanel.add(anotherProjectPanel, gbc);
    
        
        // Create a new panel for the education section
        JPanel educationPanel = new GradientPanel(Color.decode("#FEEDFF"), Color.WHITE);  // Pink to white gradient
        educationPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for positioning

        // Separate GridBagConstraints instance for the education section
        GridBagConstraints gbcEducation = new GridBagConstraints();
        gbcEducation.fill = GridBagConstraints.BOTH;  // This ensures the panel stretches both horizontally and vertically
        gbcEducation.insets = new Insets(10, 0, 10, 0); // Padding around components
        gbcEducation.weightx = 1.0;  // Stretch horizontally
        gbcEducation.weighty = 0.0;  // No extra vertical space

        // Education section label
        JLabel educationLabel = new JLabel("Education:");
        educationLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbcEducation.gridy = 0;
        gbcEducation.gridx = 0;
        gbcEducation.gridwidth = GridBagConstraints.REMAINDER;  // Span across the entire width
        educationPanel.add(educationLabel, gbcEducation);

        // First Education Block: SRI ESHWAR COLLEGE OF ENGINEERING
        JPanel collegePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Left align
        JLabel educationIcon1 = new JLabel(resizeIcon(new ImageIcon("d:\\Quick Share\\graduation.jpg"), 30, 30)); // Add your icon path
        JLabel collegeLabel = new JLabel("SRI ESHWAR COLLEGE OF ENGINEERING");
        collegeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Add icon and college label to the panel
        collegePanel.setBackground(new Color(255, 255, 255, 0)); // Transparent background
        collegePanel.add(educationIcon1);
        collegePanel.add(collegeLabel);

        // Adding the college panel to the education panel
        gbcEducation.gridy = 1;  // Move to the next row
        gbcEducation.insets = new Insets(10, 25, 10, 25);
        gbcEducation.gridwidth = GridBagConstraints.REMAINDER; // Make this panel full width
        educationPanel.add(collegePanel, gbcEducation);

        // College details (Dates and CGPA)
        JLabel collegeDetails = new JLabel("2023-2027 | CGPA: 7.82");
        collegeDetails.setFont(new Font("Arial", Font.PLAIN, 14));
        gbcEducation.insets = new Insets(10, 25, 10, 25);
        gbcEducation.gridy = 2;
        gbcEducation.gridwidth = GridBagConstraints.REMAINDER;
        educationPanel.add(collegeDetails, gbcEducation);

        // Degree details
        JLabel degreeLabel1 = new JLabel("Bachelors in Electronics and Communication Engineering");
        degreeLabel1.setFont(new Font("Arial", Font.PLAIN, 14));
        gbcEducation.gridy = 3;
        gbcEducation.gridwidth = GridBagConstraints.REMAINDER;
        educationPanel.add(degreeLabel1, gbcEducation);

        // Second Education Block: School (12th Standard)
        JPanel schoolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Left align
        JLabel educationIcon2 = new JLabel(resizeIcon(new ImageIcon("d:\\Quick Share\\graduation.jpg"), 30, 30)); // Add your icon path
        JLabel schoolLabel = new JLabel("Adharsh Vidhalaya Metric Hr Sec School");
        schoolLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Add icon and school label to the panel
        schoolPanel.setBackground(new Color(255, 255, 255, 0)); // Transparent background
        schoolPanel.add(educationIcon2);
        schoolPanel.add(schoolLabel);

        // Adding the school panel to the education panel
        gbcEducation.gridy = 4;  // Move to the next row
        gbcEducation.gridwidth = GridBagConstraints.REMAINDER;
        educationPanel.add(schoolPanel, gbcEducation);

        // School details (Dates and Percentage)
        JLabel schoolDetails = new JLabel("2021-2023 | Percentage: 78%");
        schoolDetails.setFont(new Font("Arial", Font.PLAIN, 14));
        gbcEducation.gridy = 5;
        gbcEducation.gridwidth = GridBagConstraints.REMAINDER;
        educationPanel.add(schoolDetails, gbcEducation);

        // Standard details for 12th standard
        JLabel standardLabel = new JLabel("Standard - 12th (HSLC)");
        standardLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbcEducation.gridy = 6;
        gbcEducation.gridwidth = GridBagConstraints.REMAINDER;
        educationPanel.add(standardLabel, gbcEducation);

        // Third Education Block: School (10th Standard)
        JPanel schoolPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Left align
        JLabel educationIcon3 = new JLabel(resizeIcon(new ImageIcon("d:\\Quick Share\\graduation.jpg"), 30, 30)); // Add your icon path
        JLabel schoolLabel1 = new JLabel("Adharsh Vidhalaya Metric Hr Sec School (10th)");
        schoolLabel1.setFont(new Font("Arial", Font.BOLD, 16));

        // Add icon and school label to the panel
        schoolPanel1.setBackground(new Color(255, 255, 255, 0)); // Transparent background
        schoolPanel1.add(educationIcon3);
        schoolPanel1.add(schoolLabel1);

        // Adding the school panel (10th) to the education panel
        gbcEducation.gridy = 7;
        gbcEducation.gridwidth = GridBagConstraints.REMAINDER;
        educationPanel.add(schoolPanel1, gbcEducation);

        // School details for 10th standard
        JLabel schoolDetails1 = new JLabel("2019-2021 | Percentage: 85%");
        schoolDetails1.setFont(new Font("Arial", Font.PLAIN, 14));
        gbcEducation.gridy = 8;
        gbcEducation.gridwidth = GridBagConstraints.REMAINDER;
        educationPanel.add(schoolDetails1, gbcEducation);

        // Standard details for 10th standard
        JLabel degreeLabel2 = new JLabel("10th Standard");
        degreeLabel2.setFont(new Font("Arial", Font.PLAIN, 14));
        gbcEducation.gridy = 9;
        gbcEducation.gridwidth = GridBagConstraints.REMAINDER;
        educationPanel.add(degreeLabel2, gbcEducation);

        // Finally, add the education panel to your main panel
        GridBagConstraints gbcMain1 = new GridBagConstraints();
        gbcMain1.gridx = 0;
        gbcMain1.gridy = 15;  // Adjust based on the layout
        gbcMain1.fill = GridBagConstraints.BOTH;  // Make the education panel span the full width
        gbcMain1.weightx = 1.0;  // Full horizontal stretch
        gbcMain1.weighty = 0.0;  // No extra vertical space
        mainPanel.add(educationPanel, gbcMain1);

        // Certificate section

        certificateLabel = new JLabel("Certificates:");
        certificateLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridy = 20;
        gbc.gridx = 0;
        mainPanel.add(certificateLabel, gbc);

        String[] columns = {"Certificate", "Organization", "Year", "Action"};
        String[][] data = {
            {"Figma Course", "Guvi Geek Networks", "2024", "View Certificate"},
            {"Cloud Computing", "IITM Research Park", "2023", "View Certificate"},
            {"AI Foundations", "NPTEL", "2023", "View Certificate"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Only allow editing (button press) in the Action column
            }
        };

        certificateTable = new JTable(model);

        // Set preferred column widths
        certificateTable.getColumnModel().getColumn(0).setPreferredWidth(200); // Certificate
        certificateTable.getColumnModel().getColumn(1).setPreferredWidth(200); // Organization
        certificateTable.getColumnModel().getColumn(2).setPreferredWidth(100); // Year
        certificateTable.getColumnModel().getColumn(3).setPreferredWidth(150); // Action

        // Set a fixed row height
        certificateTable.setRowHeight(30); // Adjust as needed

        certificateTable.getColumn("Action").setCellRenderer(new ButtonRenderer());
        certificateTable.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox()));

        // Create a horizontal scroll pane
        JScrollPane certificateScrollPane = new JScrollPane(certificateTable);
        certificateScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        certificateScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Remove empty space by setting preferred size for the table
        certificateTable.setPreferredScrollableViewportSize(new Dimension(600, model.getRowCount() * 30)); // Adjust height based on row count

        gbc.gridy = 21;
        mainPanel.add(certificateScrollPane, gbc);

                // Create a new panel for the contact section
        JPanel contactPanel = new JPanel(new GridBagLayout());  // Use GridBagLayout for consistency
        contactPanel.setBackground(Color.decode("#1E1D43"));  // Set the background color to Dark Blue (#1E1D43)
        gbc.insets = new Insets(5, 0, 5, 0);  // Default insets for padding

        // Contact section title
        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setFont(new Font("Arial", Font.BOLD, 18));
        contactLabel.setForeground(Color.decode("#FFC900"));  // Setting the text color to match the image
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 0, 5, 0);
        contactPanel.add(contactLabel, gbc);
        gbc.insets = new Insets(5, 25, 5, 25);

        // "Get in touch" label
        gbc.gridy++;
        JLabel touch = new JLabel("Get in touch");
        touch.setFont(new Font("Arial", Font.BOLD, 20));
        touch.setForeground(Color.WHITE);
        contactPanel.add(touch, gbc);

        // Contact description
        gbc.gridy++;
        JLabel tDetail = new JLabel("Connect with me on social media or send me a message using the form below.");
        tDetail.setFont(new Font("Arial", Font.BOLD, 16));
        tDetail.setForeground(Color.WHITE);
        contactPanel.add(tDetail, gbc);

        // Create LinkedIn, Instagram, and YouTube labels with icons
        JLabel linkedinLabel1 = new JLabel();
        JLabel instagramLabel1 = new JLabel();
        JLabel youtubeLabel1 = new JLabel();

        // Set icons for social media (adjust paths as needed)
        linkedinLabel1.setIcon(resizeIcon(new ImageIcon("d:\\Quick Share\\Linkedin.jpg"), 50, 50));
        instagramLabel1.setIcon(resizeIcon(new ImageIcon("d:\\Quick Share\\instagram.jpg"), 50, 50));
        youtubeLabel1.setIcon(resizeIcon(new ImageIcon("d:\\Quick Share\\youtube.jpg"), 50, 50));

        // Set text positions and hand cursor for interactivity
        linkedinLabel1.setHorizontalTextPosition(JLabel.CENTER);
        linkedinLabel1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        instagramLabel1.setHorizontalTextPosition(JLabel.CENTER);
        instagramLabel1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        youtubeLabel1.setHorizontalTextPosition(JLabel.CENTER);
        youtubeLabel1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add click events for social media links
        linkedinLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openLink("https://www.linkedin.com/in/dharaneeshguhan-t-a36140284");
            }
        });

        instagramLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openLink("https://www.instagram.com/ig_dharaneeshguhan");
            }
        });

        youtubeLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openLink("https://youtube.com/@ihtamil-dg");
            }
        });

        // Create a panel for social media icons
        JPanel socialMediaPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        socialMediaPanel1.setBackground(Color.decode("#1E1D43"));
        socialMediaPanel1.add(linkedinLabel1);
        socialMediaPanel1.add(instagramLabel1);
        socialMediaPanel1.add(youtubeLabel1);

        // Add social media panel to contact panel
        gbc.gridy++;
        contactPanel.add(socialMediaPanel1, gbc);

        // Contact form labels and fields
        int radius = 20;  // Rounded corners for text fields and buttons

        // Full name field
        gbc.gridy++;
        JLabel nameFieldLabel = new JLabel("Full name");
        nameFieldLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        nameFieldLabel.setForeground(Color.WHITE);
        contactPanel.add(nameFieldLabel, gbc);

        gbc.gridy++;
        RoundedTextField nameField = new RoundedTextField(20, radius);
        nameField.setBackground(Color.decode("#FBEFEF"));  // Light pink background
        nameField.setForeground(Color.decode("#2C3E50"));  // Dark text
        nameField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Padding
        contactPanel.add(nameField, gbc);

        // Email field
        gbc.gridy++;
        JLabel emailFieldLabel = new JLabel("Email");
        emailFieldLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        emailFieldLabel.setForeground(Color.WHITE);
        contactPanel.add(emailFieldLabel, gbc);

        gbc.gridy++;
        RoundedTextField emailField = new RoundedTextField(20, radius);
        emailField.setBackground(Color.decode("#FBEFEF"));  // Light pink background
        emailField.setForeground(Color.decode("#2C3E50"));  // Dark text
        emailField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Padding
        contactPanel.add(emailField, gbc);

        // Message field (using the new RoundedTextArea with corner cuts)
        gbc.gridy++;
        JLabel messageFieldLabel = new JLabel("Message");
        messageFieldLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        messageFieldLabel.setForeground(Color.WHITE);
        contactPanel.add(messageFieldLabel, gbc);

        // Create the RoundedTextArea with 5 rows and 20 columns
        gbc.gridy++;
        RoundedTextArea messageField = new RoundedTextArea(5, 20, radius);  // Specify rows, columns, and radius for corners
        messageField.setBackground(Color.decode("#FBEFEF"));  // Light pink background for text area
        messageField.setForeground(Color.decode("#2C3E50"));  // Dark text color
        messageField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Padding inside the text area
        messageField.setOpaque(false);  // Ensure the JTextArea is transparent

        // Add JScrollPane to make it scrollable
        JScrollPane messageScrollPane = new JScrollPane(messageField);
        messageScrollPane.setOpaque(false);  // Make the JScrollPane transparent
        messageScrollPane.getViewport().setOpaque(false);  // Ensure the viewport is also transparent
        messageScrollPane.setBorder(BorderFactory.createEmptyBorder());  // Remove borders around the JScrollPane

// Add the scroll pane to the contact panel
contactPanel.add(messageScrollPane, gbc);

        // Submit button
        gbc.gridy++;
        gbc.insets = new Insets(15, 25, 10, 25);  // Add extra padding for the button
        RoundedButton submitButton = new RoundedButton("Submit", radius);
        submitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        submitButton.setBackground(Color.decode("#FFC900"));  // Button background color
        submitButton.setForeground(Color.WHITE);  // White text for the button
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));  // Change cursor to hand on hover

        // Submit button action listener
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String message = messageField.getText();
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
            System.out.println("Message: " + message);

            // Clear fields after submission
            nameField.setText("");
            emailField.setText("");
            messageField.setText("");
        });

        // Add submit button to contact panel
        contactPanel.add(submitButton, gbc);

        // Add the contact panel to the main panel
        gbc.gridy = 22;  // Adjust the Y position in the main panel
        gbc.insets = new Insets(5, 0, 5, 0);  // Adjust insets for main panel
        mainPanel.add(contactPanel, gbc);
        // Add this code where you define the mainPanel and other components
        // "Go to Top" Button
        JButton goToTopButton = new JButton("go yo") {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);  // Set button color to white
                g2.fill(new Ellipse2D.Double(0, 0, getWidth(), getHeight()));
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(40, 40);  // Circular size
            }

            @Override
            public Dimension getMinimumSize() {
                return new Dimension(40, 40);  // Minimum circular size
            }

            @Override
            public Dimension getMaximumSize() {
                return new Dimension(40, 40);  // Maximum circular size
            }
        };

        goToTopButton.setIcon(new ImageIcon("path/to/arrow-icon.png"));  // Add the arrow icon
        goToTopButton.setContentAreaFilled(false);  // Remove default button background
        goToTopButton.setFocusPainted(false);  // Remove focus ring
        goToTopButton.setBorderPainted(false);  // Remove border
        goToTopButton.setBounds(740,520,40,40);
        gbc.gridy = 23;
        mainPanel.add(goToTopButton, gbc);  // Align to the left
        mainPanel.setOpaque(false);  // Transparent panel background

        // Add functionality to scroll to the top
        goToTopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainScrollPane.getVerticalScrollBar().setValue(0);  // Scroll to top when clicked
            }
        });


        // Thank You section
        gbc.insets = new Insets(5, 0, 5, 0);
        thankYouLabel = new JLabel("Thank you for visiting my portfolio!");
        thankYouLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        gbc.gridx=0;
        gbc.gridy = 31; // Adjust for the thank you label position
        mainPanel.add(thankYouLabel, gbc);

        // Ensure the mainScrollPane is added correctly to the frame
        this.add(mainScrollPane, BorderLayout.CENTER);

        bottomlogoLabel = new JLabel("Dharaneesh Guhan");
        bottomlogoLabel.setIcon(resizeIcon(new ImageIcon("d:\\Quick Share\\logo.jpg"), 50, 50));
        gbc.gridy=33;
        gbc.gridx=0;
        mainPanel.add(bottomlogoLabel, gbc);
        mainPanel.setBackground(Color.WHITE);
        this.pack();
        this.setVisible(true);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    

    // Helper method for creating individual project containers
    private JPanel createProjectContainer(String title, String description, String linkText) {
        JPanel projectContainer = new JPanel(new GridBagLayout());  // Create a new panel for each project
        projectContainer.setBackground(Color.decode("#FFFFFF"));  // Set background color to white
        projectContainer.setBorder(BorderFactory.createLineBorder(Color.decode("#E0E0E0"), 1));  // Light border around each container

        GridBagConstraints projectGbc = new GridBagConstraints();
        projectGbc.insets = new Insets(10, 10, 10, 10);  // Padding inside each project container
        projectGbc.anchor = GridBagConstraints.CENTER;  // Center the elements

        // Project title
        JLabel projectTitle = new JLabel(title);
        projectTitle.setFont(new Font("Arial", Font.BOLD, 20));
        projectTitle.setForeground(Color.decode("#FF8C00"));  // Set title color to orange
        projectGbc.gridx = 0;
        projectGbc.gridy = 0;
        projectContainer.add(projectTitle, projectGbc);

        // Project description
        JLabel projectDescription = new JLabel("<html><p style='width:250px;'>" + description + "</p></html>");
        projectDescription.setFont(new Font("Arial", Font.PLAIN, 14));
        projectDescription.setForeground(Color.decode("#333333"));  // Set text color to dark gray
        projectGbc.gridy++;  // Move to the next row
        projectContainer.add(projectDescription, projectGbc);

        // Project link button
        JButton projectLinkButton = new JButton(linkText);
        projectLinkButton.setFont(new Font("Arial", Font.PLAIN, 16));
        projectLinkButton.setBackground(Color.decode("#FFD700"));  // Set button background color to yellow
        projectLinkButton.setForeground(Color.WHITE);  // Set button text color to white
        projectLinkButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));  // Add padding inside the button
        projectLinkButton.setFocusPainted(false);  // Remove focus border around the button
        projectGbc.gridy++;  // Move to the next row
        projectContainer.add(projectLinkButton, projectGbc);

        return projectContainer;
    }
         // Helper method to create and add skill labels with icons
    private static void addSkillsWithIcons(JPanel panel, String[] skills, String[] icons) {
        for (int i = 0; i < skills.length; i++) {
            JLabel skillLabel = new JLabel(skills[i]);
            skillLabel.setOpaque(true);
            skillLabel.setBackground(Color.DARK_GRAY);
            skillLabel.setForeground(Color.WHITE);
            skillLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            skillLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

            // Add icon to the label if available
            if (i < icons.length) {
                ImageIcon icon = new ImageIcon(icons[i]); // Ensure the image paths are correct
                Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                skillLabel.setIcon(new ImageIcon(img));
                skillLabel.setHorizontalTextPosition(JLabel.RIGHT);
                skillLabel.setIconTextGap(10);
            }

            panel.add(skillLabel);
        }
        }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
     // Renderer for the "View Certificate" button in the table
     class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }
    
    // Editor for the "View Certificate" button in the table
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean clicked;
        private int row;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
        }

        

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            clicked = true;
            this.row = row;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (clicked) {
                // Action to open certificate URL
                String url = switch (row) {
                    case 0 -> "https://www.example.com/figma_certificate";
                    case 1 -> "https://www.example.com/cloud_certificate";
                    case 2 -> "https://www.example.com/ai_certificate";
                    default -> "";
                };
                openLink(url);
            }
            clicked = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
        }


    private void openLink(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void scrollToSection(int rowIndex) {
        JScrollBar vertical = mainScrollPane.getVerticalScrollBar();
        int targetY = mainPanel.getComponent(rowIndex).getY();
        vertical.setValue(targetY);
    }
    class CustomPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Set background color
            g.setColor(Color.decode("#FFFFFF"));  // You can change this to any color
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
    class GradientPanel extends JPanel {
        private Color color1;
        private Color color2;
    
        public GradientPanel(Color color1, Color color2) {
            this.color1 = color1;  // Starting color of gradient
            this.color2 = color2;  // Ending color of gradient
            setOpaque(false);  // Ensure background is transparent to apply gradient
        }
    
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
    
            // Enable anti-aliasing for smoother gradients
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
            // Create the gradient from color1 to color2
            GradientPaint gradient = new GradientPaint(
                0, 0, color1,   // Starting point (top) with color1
                0, getHeight(), color2); // Ending point (bottom) with color2
    
            // Apply the gradient to the entire panel background
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());
    
            // Call the super class's paintComponent method
            super.paintComponent(g);
        }
    }    
        // Custom JTextField with background and rounded corners
    class RoundedTextField extends JTextField {
        private int radius;

        RoundedTextField(int columns, int radius) {
            super(columns);
            this.radius = radius;
            setOpaque(false);  // Transparent to remove the default rectangular box
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Paint the background with rounded corners
            g2.setColor(Color.decode("#FBEFEF"));  // Light pink background
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            // Optionally, paint a border with a custom color if needed
            g.setColor(Color.GRAY);  // Border color
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        }
    }

    // Custom JButton with background and rounded corners
    class RoundedButton extends JButton {
        private int radius;

        RoundedButton(String text, int radius) {
            super(text);
            this.radius = radius;
            setOpaque(false);  // Transparent to remove the default rectangular box
            setContentAreaFilled(false);  // Disable default button background
        }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Paint the background with rounded corners
        g2.setColor(Color.decode("#FFC900"));  // Button background color
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Optionally, paint a border with a custom color if needed
        g.setColor(Color.GRAY);  // Border color
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
    }
    }
        public class RoundedTextArea extends JTextArea {
            private int radius;

            public RoundedTextArea(int rows, int columns, int radius) {
                super(rows, columns);
                this.radius = radius;
                setOpaque(false);  // We need this for custom painting
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fill the rounded area
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

                // Draw the text area with corner cut design
                g2.setColor(getForeground());
                super.paintComponent(g2);

                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw the border for the corner-cut effect
                g2.setColor(getForeground());
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
                
                g2.dispose();
            }
        }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(PortfolioApp::new);
    }
}