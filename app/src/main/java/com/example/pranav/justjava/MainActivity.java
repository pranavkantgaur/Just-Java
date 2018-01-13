package com.example.pranav.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    static int quantity = 0;
    final int pricePerUnit = 5;
    static boolean isWhippedCreamChecked = false;
    static boolean isChocolateChecked = false;
    // this method is called when 'ORDER' button is pressed.
    public void submitOrder(View view)
    {
        orderSummary();
    }

    private void orderSummary()
    {
        String message = "Name: Mr. Pranav";
        message += "\nAdd whipped cream?";
        if (isWhippedCreamChecked)
            message += "true";
        else
            message += "false";
        message += "\nAdd chocolate?";
        if (isChocolateChecked)
            message += "true";
        else
            message += "false";
        message += "\nQuantity: " + quantity;
        message += "\nTotal: #" + quantity * pricePerUnit;
        message += "\nThank you!";
        TextView view = (TextView) findViewById(R.id.order_summary_textview);
        view.setText(message);

    }


    public void increaseQuantity(View view)
    {
        quantity++;
        displayQuantity(quantity);
    }

    public void decreaseQuantity(View view)
    {
        if (quantity > 1)
            quantity--;
        else
            quantity = 0;
        displayQuantity(quantity);
    }

    private void displayQuantity(int number)
    {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void setIsWhippedCreamChecked(View view) {
        CheckBox whippedCreamCB = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        if (whippedCreamCB.isChecked())
            isWhippedCreamChecked = true;
        else
            isWhippedCreamChecked = false;
    }

    public void setIsChocolateChecked(View view)
    {
        CheckBox chocolateCB = (CheckBox) findViewById(R.id.chocolate_checkbox);
        if (chocolateCB.isChecked())
            isChocolateChecked = true;
        else
            isChocolateChecked = false;
    }
}
