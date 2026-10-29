package dev.pranav.applock.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.pranav.applock.ui.theme.DotMatrixFontFamily
import dev.pranav.applock.ui.theme.NothingBlack
import dev.pranav.applock.ui.theme.NothingBorder
import dev.pranav.applock.ui.theme.NothingRed
import dev.pranav.applock.ui.theme.NothingSage
import dev.pranav.applock.ui.theme.NothingSurface
import dev.pranav.applock.ui.theme.NothingTextPrimary
import dev.pranav.applock.ui.theme.NothingTextSecondary

/**
 * BentoCard: Core Squircle container for Nothing OS 3.0–5.0 Bento Grid layout.
 * Features 28dp–36dp corner radii, subtle 1px border, solid anthracite background, and no drop shadows.
 */
@Composable
fun BentoCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    backgroundColor: Color = NothingSurface,
    borderColor: Color = NothingBorder,
    borderWidth: Dp = 1.dp,
    shape: Shape = RoundedCornerShape(28.dp),
    badge: String? = null,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor)
            .border(borderWidth, borderColor, shape)
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onClick
                    )
                } else Modifier
            )
    ) {
        content()

        badge?.let {
            CornerBadgePin(
                text = it,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-12).dp, y = 12.dp)
            )
        }
    }
}

/**
 * PillButton: Capsule button (9999dp) for high-contrast primary and secondary actions.
 */
@Composable
fun PillButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    containerColor: Color = NothingTextPrimary,
    contentColor: Color = NothingBlack,
    borderColor: Color = Color.Transparent,
    enabled: Boolean = true
) {
    Surface(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.height(52.dp),
        shape = CircleShape,
        color = if (enabled) containerColor else NothingSurface,
        contentColor = if (enabled) contentColor else NothingTextSecondary,
        border = if (borderColor != Color.Transparent) BorderStroke(1.dp, borderColor) else null
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    tint = if (enabled) contentColor else NothingTextSecondary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            DotMatrixText(
                text = text,
                color = if (enabled) contentColor else NothingTextSecondary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

/**
 * CircleButton: 1x1 Aspect Ratio action toggle button.
 */
@Composable
fun CircleButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    text: String? = null,
    containerColor: Color = NothingSurface,
    contentColor: Color = NothingTextPrimary,
    borderColor: Color = NothingBorder,
    size: Dp = 56.dp
) {
    Box(
        modifier = modifier
            .size(size)
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(containerColor)
            .border(1.dp, borderColor, CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(size * 0.45f)
            )
        } else if (text != null) {
            DotMatrixText(
                text = text,
                color = contentColor,
                fontSize = (size.value * 0.32f).sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

/**
 * DotMatrixText: Monospace Dot-Matrix header/label with forced UPPERCASE formatting.
 */
@Composable
fun DotMatrixText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = NothingTextPrimary,
    fontSize: androidx.compose.ui.unit.TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    letterSpacing: androidx.compose.ui.unit.TextUnit = 1.2.sp,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        text = text.uppercase(),
        modifier = modifier,
        style = TextStyle(
            fontFamily = DotMatrixFontFamily,
            fontWeight = fontWeight,
            fontSize = fontSize,
            letterSpacing = letterSpacing,
            color = color
        ),
        maxLines = maxLines,
        overflow = overflow
    )
}

/**
 * CornerBadgePin: Red/Achromatic circular badge pin (24px x 24px) for notification/status overlay.
 */
@Composable
fun CornerBadgePin(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = NothingRed,
    contentColor: Color = NothingTextPrimary
) {
    Box(
        modifier = modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontFamily = DotMatrixFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                color = contentColor
            )
        )
    }
}

/**
 * BentoToggle: Custom Nothing OS Pill Toggle Switch (Sage Green active, Anthrazit inactive).
 */
@Composable
fun BentoToggle(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    activeColor: Color = NothingSage,
    inactiveColor: Color = NothingSurface
) {
    val trackColor by animateColorAsState(
        targetValue = if (checked) activeColor else inactiveColor,
        animationSpec = tween(200),
        label = "trackColor"
    )
    val thumbOffset by animateDpAsState(
        targetValue = if (checked) 24.dp else 2.dp,
        animationSpec = tween(200),
        label = "thumbOffset"
    )

    Box(
        modifier = modifier
            .width(52.dp)
            .height(30.dp)
            .clip(CircleShape)
            .background(trackColor)
            .border(1.dp, NothingBorder, CircleShape)
            .clickable { onCheckedChange(!checked) },
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .offset(x = thumbOffset)
                .size(26.dp)
                .padding(2.dp)
                .clip(CircleShape)
                .background(if (checked) NothingSurface else NothingTextPrimary)
        )
    }
}

/**
 * BentoTextField: Capsule search/text input field with 1px border.
 */
@Composable
fun BentoTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .clip(CircleShape)
            .background(NothingSurface)
            .border(1.dp, NothingBorder, CircleShape)
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            leadingIcon?.let {
                it()
                Spacer(modifier = Modifier.width(12.dp))
            }
            Box(modifier = Modifier.weight(1f)) {
                if (value.isEmpty()) {
                    DotMatrixText(
                        text = placeholder,
                        color = NothingTextSecondary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    textStyle = TextStyle(
                        fontFamily = DotMatrixFontFamily,
                        fontSize = 14.sp,
                        color = NothingTextPrimary
                    ),
                    cursorBrush = SolidColor(NothingRed),
                    keyboardOptions = keyboardOptions,
                    keyboardActions = keyboardActions,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            trailingIcon?.let {
                Spacer(modifier = Modifier.width(12.dp))
                it()
            }
        }
    }
}
