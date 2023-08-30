import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazGrafica extends JFrame {
    private JTextField campoEntrada;
    private double resultadoTemporal;
    private String operadorTemporal;
    private boolean operadorPulsado;

    public InterfazGrafica() {
        setTitle("Calculadora cientifica =D");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        campoEntrada = new JTextField();
        campoEntrada.setFont(new Font("Arial", Font.PLAIN, 24));
        add(campoEntrada, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(5, 5));

        
        for (int i = 1; i <= 9; i++) {
            JButton boton = crearBotonNumero(String.valueOf(i));
            panelBotones.add(boton);
        }
        panelBotones.add(crearBotonNumero("0"));

        // Operadores Basicos
        panelBotones.add(crearBotonOperador("+"));
        panelBotones.add(crearBotonOperador("-"));
        panelBotones.add(crearBotonOperador("*"));
        panelBotones.add(crearBotonOperador("/"));
        panelBotones.add(crearBotonOperador("C"));
        panelBotones.add(crearBotonIgual());

        //Funciones trigonometricas
        panelBotones.add(crearBotonFuncionTrigonometrica("Sen"));
        panelBotones.add(crearBotonFuncionTrigonometrica("Cos"));
        panelBotones.add(crearBotonFuncionTrigonometrica("Tan"));

        // Raiz y potencia Enesima
        panelBotones.add(crearBotonFuncion("√"));
        panelBotones.add(crearBotonFuncion("^"));

        // Boton de porcentaje IVA
        panelBotones.add(crearBotonPorcentajeIVA());

        add(panelBotones, BorderLayout.CENTER);

        operadorPulsado = false;
        pack();
    }

    private JButton crearBotonNumero(String etiqueta) {
        JButton boton = new JButton(etiqueta);
        boton.setFont(new Font("Arial", Font.PLAIN, 24));
        boton.addActionListener(new NumberButtonListener());
        return boton;
    }

    private JButton crearBotonOperador(String etiqueta) {
        JButton boton = new JButton(etiqueta);
        boton.setFont(new Font("Arial", Font.PLAIN, 24));
        boton.addActionListener(new OperatorButtonListener());
        return boton;
    }

    private JButton crearBotonFuncionTrigonometrica(String etiqueta) {
        JButton boton = new JButton(etiqueta);
        boton.setFont(new Font("Arial", Font.PLAIN, 24));
        boton.addActionListener(new FunctionButtonListener());
        return boton;
    }

    private JButton crearBotonFuncion(String etiqueta) {
        JButton boton = new JButton(etiqueta);
        boton.setFont(new Font("Arial", Font.PLAIN, 24));
        boton.addActionListener(new FunctionButtonListener());
        return boton;
    }

    private JButton crearBotonPorcentajeIVA() {
        JButton boton = new JButton("IVA");
        boton.setFont(new Font("Arial", Font.PLAIN, 24));
        boton.addActionListener(new IVAButtonListener());
        return boton;
    }

    private JButton crearBotonIgual() {
        JButton boton = new JButton("=");
        boton.setFont(new Font("Arial", Font.PLAIN, 24));
        boton.addActionListener(new IgualButtonListener());
        return boton;
    }
    

    private class NumberButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            if (operadorPulsado) {
                campoEntrada.setText(boton.getText());
                operadorPulsado = false;
            } else {
                campoEntrada.setText(campoEntrada.getText() + boton.getText());
            }
        }
    }

    private class OperatorButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            if (operadorTemporal != null) {
                Calcular();
            }
            operadorTemporal = boton.getText();
            resultadoTemporal = Double.parseDouble(campoEntrada.getText());
            operadorPulsado = true;
        }
    }

    private class FunctionButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String funcion = boton.getText();

            double numero = Double.parseDouble(campoEntrada.getText());
            double resultado = 0.0;

            switch (funcion) {
                case "Sen":
                    resultado = Math.sin(Math.toRadians(numero));
                    break;
                case "Cos":
                    resultado = Math.cos(Math.toRadians(numero));
                    break;
                case "Tan":
                    resultado = Math.tan(Math.toRadians(numero));
                    break;
                case "√":
                    resultado = Math.sqrt(numero);
                    break;
                case "^":
                    resultado = Math.pow(resultadoTemporal, numero);
                    break;
            }

            campoEntrada.setText(String.valueOf(resultado));
        }
    }

    private class IVAButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double montoInicial = Double.parseDouble(campoEntrada.getText());
            double porcentajeIVA = Double.parseDouble(
                    JOptionPane.showInputDialog("Ingrese el porcentaje de IVA:"));
            double IVA = (montoInicial * porcentajeIVA) / 100;
            double precioTotal = montoInicial + IVA;
            campoEntrada.setText(String.valueOf(precioTotal));
        }
    }
    private class IgualButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Calcular();
        }
    }
    private void Calcular() {
        double segundoNumero = Double.parseDouble(campoEntrada.getText());
        double resultado = 0.0;

        switch (operadorTemporal) {
            case "+":
                resultado = resultadoTemporal + segundoNumero;
                break;
            case "-":
                resultado = resultadoTemporal - segundoNumero;
                break;
            case "*":
                resultado = resultadoTemporal * segundoNumero;
                break;
            case "/":
                if (segundoNumero != 0) {
                    resultado = resultadoTemporal / segundoNumero;
                } else {
                    JOptionPane.showMessageDialog(this, "Error: División por 0");
                }
                break;
        }

        campoEntrada.setText(String.valueOf(resultado));
        operadorTemporal = null;
    }
   

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfazGrafica calc = new InterfazGrafica();
            calc.setVisible(true);
        });
    }
}
