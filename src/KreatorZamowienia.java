import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KreatorZamowienia extends JFrame {

    private CardLayout ukladKart;
    private JPanel panelKart;

    private JTextField txtProdukt;
    private JTextField txtIlosc;
    private JLabel lblPodsumowanie;
    private int nrKarty = 1;

    public KreatorZamowienia() {
        setTitle("Kreator zamówienia (CardLayout)");
        setSize(450, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));
        setLocationRelativeTo(null);

        ukladKart = new CardLayout(); // tworzymy układ ukladKart
        panelKart = new JPanel(ukladKart); // ustawiamy w panelKart układ ukladKart

        // === KARTA 1 – Produkt ===
        JPanel panelKrok1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        panelKrok1.add(new JLabel("Krok 1: Produkt"));
        txtProdukt = new JTextField(15);
        panelKrok1.add(txtProdukt);
        JButton btnDalej = new JButton("Dalej");
        panelKrok1.add(btnDalej);

        // === KARTA 2 – Ilość ===
        JPanel panelKrok2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        panelKrok2.add(new JLabel("Krok 2: Ilość"));
        txtIlosc = new JTextField(15);
        panelKrok2.add(txtIlosc);
        JButton btnWstecz2 = new JButton("Wstecz");
        panelKrok2.add(btnWstecz2);
        JButton btnDalej2 = new JButton("Dalej");
        panelKrok2.add(btnDalej2);

        // === KARTA 3 – Podsumowanie ===
        JPanel panelKrok3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        panelKrok3.add(new JLabel("Krok 3: Podsumowanie"));
        lblPodsumowanie = new JLabel("");
        panelKrok3.add(lblPodsumowanie);
        JButton btnWstecz3 = new JButton("Wstecz");
        panelKrok3.add(btnWstecz3);
        JButton btnZamow = new JButton("Zamów");
        panelKrok3.add(btnZamow);

        // Dodajemy karty do panelu kart – każda z unikalną nazwą
        panelKart.add(panelKrok1, "krok1");
        panelKart.add(panelKrok2, "krok2");
        panelKart.add(panelKrok3, "krok3");

        // Panel kart umieszczamy w CENTER okna
        add(panelKart, BorderLayout.CENTER);

        // === Etykieta informacyjna na górze ===
        JLabel lblNaglowek = new JLabel("Kreator zamówienia", SwingConstants.CENTER);
        lblNaglowek.setFont(new Font("Arial", Font.BOLD, 16));
        lblNaglowek.setOpaque(true);
        lblNaglowek.setBackground(new Color(60, 120, 180));
        lblNaglowek.setForeground(Color.WHITE);
        add(lblNaglowek, BorderLayout.NORTH);

        // === Obsługa zdarzeń – nawigacja między kartami ===
        // Karta 1 → Karta 2
        btnDalej.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtProdukt.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Podaj produkt");
                    return;
                }
                ukladKart.show(panelKart, "krok2"); // przełączamy na kartę "krok2"
            }
        });

        // Karta 2 → Karta 1
        btnWstecz2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ukladKart.show(panelKart, "krok1"); // wracamy do karty "krok1"
            }
        });

        // Karta 2 → Karta 3
        btnDalej2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtIlosc.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Podaj ilość");
                    return;
                }
                lblPodsumowanie.setText("Produkt: " + txtProdukt.getText() + ", Ilość: " + txtIlosc.getText());
                ukladKart.show(panelKart, "krok3");
            }
        });

        // Karta 3 → Karta 2
        btnWstecz3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ukladKart.show(panelKart, "krok2");
            }
        });

        // Zakończenie kreatora
        btnZamow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Zamówienie złożone!\nProdukt: " + txtProdukt.getText()
                        + "\nIlość: " + txtIlosc.getText());
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new KreatorZamowienia();
    }
}