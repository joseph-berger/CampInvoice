import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InvoiceGUI extends JFrame implements ActionListener {
    private JTextField productNameField, unitPriceField, quantityField;
    private JButton addLineItemButton, calculateTotalButton, displayInvoiceButton;
    private JTextArea invoiceTextArea;
    private Invoice invoice;
    private List<LineItem> lineItems;

    public InvoiceGUI() {
        super("Invoice Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        productNameField = new JTextField(20);
        unitPriceField = new JTextField(10);
        quantityField = new JTextField(5);

        addLineItemButton = new JButton("Add Line Item");
        calculateTotalButton = new JButton("Calculate Total");
        displayInvoiceButton = new JButton("Display Invoice");

        addLineItemButton.addActionListener(this);
        calculateTotalButton.addActionListener(this);
        displayInvoiceButton.addActionListener(this);

        invoiceTextArea = new JTextArea(15, 30);

        panel.add(new JLabel("Product Name:"));
        panel.add(productNameField);
        panel.add(new JLabel("Unit Price:"));
        panel.add(unitPriceField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(addLineItemButton);
        panel.add(calculateTotalButton);
        panel.add(displayInvoiceButton);
        panel.add(new JScrollPane(invoiceTextArea));

        add(panel);
    }

    public static void main(String[] args) {
        InvoiceGUI invoiceGUI = new InvoiceGUI();
        invoiceGUI.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addLineItemButton) {
            addLineItem();
        } else if (e.getSource() == calculateTotalButton) {
            calculateTotal();
        } else if (e.getSource() == displayInvoiceButton) {
            displayInvoice();
        }
    }

    private void addLineItem() {
        String productName = productNameField.getText();
        double unitPrice = Double.parseDouble(unitPriceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        Product product = new Product(productName, unitPrice);
        LineItem lineItem = new LineItem(product, quantity);

        if (lineItems == null)
            lineItems = new ArrayList<>();
        lineItems.add(lineItem);

        productNameField.setText("");
        unitPriceField.setText("");
        quantityField.setText("");
    }

    private void calculateTotal() {
        if (lineItems != null && !lineItems.isEmpty()) {
            if (invoice == null)
                invoice = new Invoice("My Invoice", "Customer Address");

            for (LineItem lineItem : lineItems) {
                invoice.addLineItem(lineItem);
            }

            double totalAmountDue = invoice.getTotalAmountDue();
            JOptionPane.showMessageDialog(this, "Total Amount Due: $" + totalAmountDue);
        } else {
            JOptionPane.showMessageDialog(this, "No line items added yet!");
        }
    }

    private void displayInvoice() {
        if (invoice != null) {
            invoiceTextArea.setText("Invoice Details:\n\n");
            invoiceTextArea.append("Title: " + invoice.getTitle() + "\n");
            invoiceTextArea.append("Customer Address: " + invoice.getCustomerAddress() + "\n\n");
            invoiceTextArea.append("Line Items:\n");

            for (LineItem lineItem : lineItems) {
                Product product = lineItem.getProduct();
                invoiceTextArea.append(product.getName() + " - Quantity: " + lineItem.getQuantity() + "\n");
            }

            invoiceTextArea.append("\nTotal Amount Due: $" + invoice.getTotalAmountDue());
        } else {
            JOptionPane.showMessageDialog(this, "No invoice generated yet!");
        }
    }
}
