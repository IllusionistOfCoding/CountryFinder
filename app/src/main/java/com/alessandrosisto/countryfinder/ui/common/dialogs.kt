package com.alessandrosisto.countryfinder.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alessandrosisto.countryfinder.ui.theme.*
import com.alessandrosisto.countryfinder.models.EntryDialog
import com.alessandrosisto.countryfinder.repo.fake.fakeEntryDialog
import com.alessandrosisto.countryfinder.utilis.typeToDisplayTitle

@Composable
fun ListItemsDialog(
    list: List<EntryDialog>,
    type: String,
    selectedItem: EntryDialog,
    onItemSelected: (EntryDialog) -> Unit,
    onDismissDialog: () -> Unit
) {
    var selected: String by remember { mutableStateOf(selectedItem.code) }
    Box(
        modifier = Modifier
            .background(bgDialog)
            .padding(all = 16.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(start = 12.dp, bottom = 12.dp)
                .align(Alignment.TopStart),
            text = type.typeToDisplayTitle(),
            color = textColor,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        LazyColumn(
            modifier = Modifier
                .padding(top = 32.dp, bottom = 32.dp)
                .heightIn(0.dp, 380.dp)
                .align(Alignment.CenterStart)
        ) {
            items(list) {
                SingleChoiceItem(
                    it,
                    selected == it.code
                ) { code ->
                    selected = code
                }
            }
        }
        Row(
            modifier = Modifier.align(Alignment.BottomEnd),
        ) {
            ActionButton(
                text = "Cancel",
                onClick = onDismissDialog
            )
            ActionButton(
                text = "OK",
                onClick = {
                    onItemSelected(list.first { it.code == selected })
                }
            )
        }
    }
}

/**
 * Adds a negative or positive button to the dialog
 *
 * @param text the string literal text shown in the button
 * @param res the string resource text shown in the button
 * even if autoDismissing is disabled
 * @param onClick a callback which is called when the button is pressed
 */
@Composable
fun ActionButton(
    text: String = "button",
    textStyle: TextStyle = MaterialTheme.typography.button,
    onClick: () -> Unit = {}
) {
    TextButton(
        onClick = {
            onClick()
        },
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            color = primaryColor,
            style = textStyle
        )
    }
}

@Composable
private fun SingleChoiceItem(
    item: EntryDialog,
    selected: Boolean,
    onSelect: (code: String) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable {
                onSelect(item.code)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            colors = RadioButtonDefaults.colors(
                selectedColor = accentColor,
                unselectedColor = secondaryTextColor
            ),
            selected = selected,
            onClick = null,
        )
        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .width(24.dp)
        )
        Text(
            text = item.name ?: "none",
            color = textColor,
            fontSize = 16.sp,
            style = MaterialTheme.typography.body1
        )
    }
}

@Preview("ListItemsDialog", showBackground = true)
@Composable
fun PreviewListItemsDialog() {
    ListItemsDialog(fakeEntryDialog, "Languages", EntryDialog(code = "af"), {}, {})
}