package com.kiienkoromaniuk.sunshineandroid.view.button

import androidx.annotation.DimenRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.SemanticsProperties.TestTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kiienkoromaniuk.sunshineandroid.view.text.ButtonText
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@Composable
fun BrandButton(
    text: String?,
    textColor: Color,
    backgroundColor: Color,
    disabledBackgroundColor: Color,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    radius: Dp = 0.dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    isLoading: Boolean = false,
    progressModifier: Modifier = Modifier.size(28.dp),
    progressIndicatorColor: Color = BrandTheme.colors.N000,
    progressIndicatorStrokeWidth: Dp = ProgressIndicatorDefaults.StrokeWidth,
    onButtonClicked: () -> Unit = {},
) {
    Button(
        onClick = onButtonClicked,
        modifier = modifier,
        enabled = isEnabled && !isLoading,
        elevation = null,
        contentPadding = contentPadding,
        shape = RoundedCornerShape(size = radius),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            disabledBackgroundColor = disabledBackgroundColor,
        ),
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = progressIndicatorColor,
                modifier = progressModifier,
                strokeWidth = progressIndicatorStrokeWidth,
            )
        } else {
            ButtonText(
                text = text.orEmpty(),
                color = textColor,
            )
        }
    }
}
