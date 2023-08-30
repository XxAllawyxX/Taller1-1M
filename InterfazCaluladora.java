import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfazCaluladora extends JFrame {
    private JTextField campoEntrada;
    private JButton[] NumeroBotones;
    private JButton Suma, Resta, Multiplicacion, Division, Igual, Limpiar;
    private double numero1, numero2, resultado;
    private char operador;

    public InterfazCaluladora() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(5, 4, 10, 10));

        campoEntrada = new JTextField();
        campoEntrada.setFont(new Font("Arial", Font.PLAIN, 24));
        campoEntrada.setHorizontalAlignment(JTextField.RIGHT);
        campoEntrada.setEditable(false);

        NumeroBotones = new JButton[10];
        for (int i = 0; i < 10; i++) {
            NumeroBotones[i] = new JButton(String.valueOf(i));
            NumeroBotones[i].setFont(new Font("Arial", Font.PLAIN, 24));
            NumeroBotones[i].addActionListener(new NumberButtonListener());
        }

        Suma = crearBotonOperador("+");
        Resta = crearBotonOperador("-");
        Multiplicacion = crearBotonOperador("*");
        Division = crearBotonOperador("/");
        Igual = crearBotonOperador("=");
        Limpiar = crearBotonOperador("C");

        add(campoEntrada);

        for (int i = 1; i <= 9; i++) {
            add(NumeroBotones[i]);
        }

        add(Limpiar);
        add(NumeroBotones[0]);
        add(Igual);
        add(Suma);
        add(Resta);
        add(Multiplicacion);
        add(Division);

        pack();
        setLocationRelativeTo(null);
    }

    private JButton crearBotonOperador(String etiqueta) {
        JButton boton = new JButton(etiqueta);
        boton.setFont(new Font("Arial", Font.PLAIN, 24));
        boton.addActionListener(new ButtonListenerOperator());
        return boton;
    }

    private class NumberButtonListener  implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            campoEntrada.setText(campoEntrada.getText() + boton.getText());
        }
    }

    private class ButtonListenerOperator implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            char op = boton.getText().charAt(0);

            if (op == '=') {
                numero2 = Double.parseDouble(campoEntrada.getText());
                switch (operador) {
                    case '+':
                        resultado = numero1 + numero2;
                        break;
                    case '-':
                        resultado = numero1 - numero2;
                        break;
                    case '*':
                        resultado = numero1 * numero2;
                        break;
                    case '/':
                        if (numero2 != 0) {
                            resultado = numero1 / numero2;
                        } else {
                            resultado = 0; // Manejo de división por cero
                        }
                        break;
                }
                campoEntrada.setText(String.valueOf(resultado));
            } else if (op == 'C') {
                campoEntrada.setText("");
                numero1 = numero2 = resultado = 0;
                operador = '\0';
            } else {
                numero1 = Double.parseDouble(campoEntrada.getText());
                campoEntrada.setText("");
                operador = op;
            }
        }
    }
}
