package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.yoomoney.sdk.kassa.payments.Checkout.createTokenizeIntent
import ru.yoomoney.sdk.kassa.payments.checkoutParameters.Amount
import ru.yoomoney.sdk.kassa.payments.checkoutParameters.PaymentMethodType
import ru.yoomoney.sdk.kassa.payments.checkoutParameters.PaymentParameters
import ru.yoomoney.sdk.kassa.payments.checkoutParameters.SavePaymentMethod
import java.math.BigDecimal
import java.util.Currency

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startTokenize()
    }

    fun startTokenize() {
        val paymentParameters = PaymentParameters(
            amount = Amount(BigDecimal.TEN, Currency.getInstance("RUB")),
            title = "Product name",
            subtitle = "Product description",
            clientApplicationKey = "live_MTkzODU2VY5GiyQq2GMPsCQ0PW7f_RSLtJYOT-mp_CA", // key for client apps from the YooMoney Merchant Profile
            shopId = "193856", // ID of the store in the YooMoney system
            savePaymentMethod = SavePaymentMethod.OFF, // flag of the disabled option to save payment methods,
            paymentMethodTypes = setOf(
                PaymentMethodType.BANK_CARD,
                PaymentMethodType.SBERBANK,
            ), // the full list of available payment methods has been provided
            customReturnUrl = "https://custom.redirect.url", // url of the page (only https is supported) that the user should be returned to after completing 3ds.
        )
        val intent = createTokenizeIntent(this, paymentParameters)
        startActivityForResult(intent, 101)
    }
}
