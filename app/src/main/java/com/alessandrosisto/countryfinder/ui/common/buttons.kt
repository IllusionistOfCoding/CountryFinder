package com.alessandrosisto.countryfinder.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alessandrosisto.countryfinder.models.Country
import com.alessandrosisto.countryfinder.models.EntryDialog
import com.alessandrosisto.countryfinder.repo.fake.fakeCountry
import com.alessandrosisto.countryfinder.ui.theme.CountryFinderTheme
import com.alessandrosisto.countryfinder.ui.theme.darkPrimaryColor
import com.alessandrosisto.countryfinder.ui.theme.textColor
import com.alessandrosisto.countryfinder.utilis.NONE_CODE

@Composable
fun ButtonFilter(
    modifier: Modifier,
    selectedLabel: EntryDialog,
    onClick: (String) -> Unit
) {
    Button(
        modifier = modifier,
        onClick = { onClick(selectedLabel.type) },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = darkPrimaryColor,
            contentColor = textColor
        )
        // Uses ButtonDefaults.ContentPadding by default
    ) {
        Text(text = selectedLabel.name ?: "none")
    }

}

@Preview(showBackground = true)
@Composable
fun CardCountryPreview(country: Country = fakeCountry) {
    CountryFinderTheme {
        ButtonFilter(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            EntryDialog(code = NONE_CODE)
        ) {}
    }
}