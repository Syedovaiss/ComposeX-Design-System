# ComposeX Design System

A comprehensive, customizable design system built with Jetpack Compose and Material 3. ComposeX provides a complete set of UI components following modern design principles with full theming support.

## Table of Contents

- [Overview](#overview)
- [Installation](#installation)
- [Core Concepts](#core-concepts)
- [Design Tokens](#design-tokens)
- [Components](#components)
- [Theming](#theming)
- [Customization](#customization)
- [Usage Examples](#usage-examples)
- [Architecture](#architecture)

## Overview

ComposeX Design System is a modular, scalable solution for building consistent user interfaces in Android applications. It provides:

- **20+ Pre-built Components**: Buttons, TextFields, Chips, Cards, Dialogs, and more
- **Design Tokens**: Consistent spacing, sizing, colors, and typography
- **Theme-Aware**: Automatic light/dark mode support
- **Fully Customizable**: Extend and customize every component
- **Material 3 Integration**: Built on top of Material 3 components

## Installation

### Using as a Module (Local Development)

Add the module to your project's `build.gradle.kts`:

```kotlin
dependencies {
    implementation(project(":composex-core"))
}
```

### Using as a Library (JitPack)

1. Add JitPack to your `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

2. Add the dependency to your app's `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.github.Syedovaiss:ComposeX-Design-System:v1.0.0")
}
```

## Core Concepts

### Design Tokens

Design tokens are the foundational building blocks of the design system. They provide consistent values for:

- **Colors**: Semantic color system with light/dark variants
- **Sizing**: Standardized size tokens (Small, Medium, Large, ExtraLarge)
- **Spacing**: Consistent spacing values
- **Typography**: Font sizing and styles
- **Shapes**: Border radius and shape definitions
- **Elevation**: Shadow and elevation values

### Component System

Every component follows a consistent pattern:

1. **Variants**: Visual style variants (Primary, Secondary, Success, Danger, etc.)
2. **States**: Interactive states (Default, Pressed, Disabled, Focused, Hovered)
3. **Sizes**: Size tokens for responsive design

## Design Tokens

### ColorType

Semantic color system that adapts to light/dark themes:

```kotlin
// Basic usage - defaults to light theme
ColorType.Success()
ColorType.Danger()
ColorType.Warning()
ColorType.Info()

// Explicit theme
ColorType.Success(isLight = true)
ColorType.Success(isLight = false)

// System theme detection (default when isLight is null)
ColorType.Info() // Uses system preference

// Custom colors
ColorType.Custom(color = Color(0xFFFF5722))
```

**Available Colors:**
- `SuccessLight`: `#4CAF50` - Material Green 500
- `DangerLight`: `#F44336` - Material Red 500
- `WarningLight`: `#FF9800` - Material Orange 500
- `InfoLight`: `#2196F3` - Material Blue 500
- Dark variants available for dark theme

### SizeToken

Standardized sizing across components:

```kotlin
SizeToken.Small      // 32dp equivalent
SizeToken.Medium     // 40dp equivalent (default)
SizeToken.Large      // 48dp equivalent
SizeToken.ExtraLarge // 56dp equivalent
SizeToken.Custom(64.dp) // Custom size
```

### ComponentVariant

Visual style variants:

```kotlin
ComponentVariant.Primary
ComponentVariant.Secondary
ComponentVariant.Tertiary
ComponentVariant.Success
ComponentVariant.Danger
ComponentVariant.Warning
ComponentVariant.Info
ComponentVariant.Custom(
    backgroundColor = ColorType.Custom(Color.Blue),
    contentColor = ColorType.Custom(Color.White),
    borderColor = ColorType.Custom(Color.DarkBlue),
    elevation = ElevationType.High,
    shape = ShapeType.Medium
)
```

### ComponentState

Interactive states for components:

```kotlin
ComponentState.Default
ComponentState.Pressed
ComponentState.Disabled
ComponentState.Focused
ComponentState.Hovered
```

## Components

### Buttons

Four button styles available:

#### Filled Button
```kotlin
ComposeXButton(
    text = "Click Me",
    onClick = { /* action */ },
    variant = ComponentVariant.Primary,
    sizeToken = SizeToken.Medium
)
```

#### Outlined Button
```kotlin
ComposeXOutlinedButton(
    text = "Outlined",
    onClick = { /* action */ },
    variant = ComponentVariant.Success
)
```

#### Text Button
```kotlin
ComposeXTextButton(
    text = "Text Only",
    onClick = { /* action */ },
    variant = ComponentVariant.Info
)
```

#### Filled Tonal Button
```kotlin
ComposeXFilledTonalButton(
    text = "Tonal",
    onClick = { /* action */ },
    variant = ComponentVariant.Warning
)
```

### Form Components

#### TextField
```kotlin
var text by remember { mutableStateOf("") }

ComposeXTextField(
    value = text,
    onValueChange = { text = it },
    placeholder = "Enter text...",
    variant = ComponentVariant.Primary,
    sizeToken = SizeToken.Medium,
    leadingIcon = R.drawable.ic_search,
    error = false,
    supportingText = "Helper text"
)
```

#### Checkbox
```kotlin
var checked by remember { mutableStateOf(false) }

ComposeXCheckbox(
    checked = checked,
    onCheckedChange = { checked = it },
    variant = ComponentVariant.Primary
)
```

#### Radio Button
```kotlin
var selectedOption by remember { mutableStateOf("Option 1") }

ComposeXRadioButton(
    selected = selectedOption == "Option 1",
    onClick = { selectedOption = "Option 1" },
    variant = ComponentVariant.Info
)
```

#### Switch
```kotlin
var enabled by remember { mutableStateOf(true) }

ComposeXSwitch(
    checked = enabled,
    onCheckedChange = { enabled = it },
    label = "Enable notifications",
    variant = ComponentVariant.Success
)
```

### Selection Components

#### Dropdown
```kotlin
var selectedItem by remember { mutableStateOf<DropdownItem?>(null) }
val items = listOf(
    DropdownItem(id = "1", text = "Option 1"),
    DropdownItem(id = "2", text = "Option 2")
)

ComposeXDropdown(
    selectedItem = selectedItem,
    items = items,
    onItemSelected = { selectedItem = it },
    placeholder = "Select an option",
    bordered = true
)
```

#### Chip
```kotlin
var selected by remember { mutableStateOf(false) }

ComposeXChip(
    text = "Selectable Chip",
    selected = selected,
    onSelectionChange = { selected = it },
    variant = ComponentVariant.Info,
    leadingIcon = R.drawable.ic_star
)
```

#### Counter
```kotlin
var value by remember { mutableStateOf(0) }

ComposeXCounter(
    value = value,
    onValueChange = { value = it },
    minValue = 0,
    maxValue = 10,
    variant = ComponentVariant.Primary
)
```

### Feedback Components

#### Progress Indicators
```kotlin
// Linear
ComposeXLinearProgressIndicator(
    progress = 0.7f,
    variant = ComponentVariant.Success
)

// Circular
ComposeXCircularProgressIndicator(
    progress = 0.5f,
    variant = ComponentVariant.Info
)
```

#### Badge
```kotlin
ComposeXBadge(
    count = 5,
    variant = ComponentVariant.Danger,
    maxCount = 99
)

// Badged Box
ComposeXBadgedBox(
    badgeCount = 3,
    variant = ComponentVariant.Danger
) {
    Icon(Icons.Default.Notifications, contentDescription = null)
}
```

#### Dialog
```kotlin
var showDialog by remember { mutableStateOf(false) }

if (showDialog) {
    ComposeXAlertDialog(
        onDismissRequest = { showDialog = false },
        title = "Confirm",
        text = "Are you sure?",
        confirmText = "Yes",
        dismissText = "No",
        onConfirm = { showDialog = false },
        variant = ComponentVariant.Primary
    )
}
```

#### Snackbar
```kotlin
val snackbarHostState = remember { SnackbarHostState() }

ComposeXSnackbarHost(
    hostState = snackbarHostState,
    variant = ComponentVariant.Info
)

// Show snackbar
LaunchedEffect(Unit) {
    snackbarHostState.showSnackbar(
        message = "Action completed",
        actionLabel = "Undo"
    )
}
```

### Container Components

#### Card
```kotlin
ComposeXCard(
    modifier = Modifier.fillMaxWidth(),
    variant = ComponentVariant.Primary,
    content = { paddingValues ->
        Text(
            text = "Card Content",
            modifier = Modifier.padding(paddingValues)
        )
    }
)
```

#### Elevated Card
```kotlin
ComposeXElevatedCard(
    modifier = Modifier.fillMaxWidth(),
    variant = ComponentVariant.Secondary,
    content = { paddingValues ->
        // Content here
    }
)
```

#### Bottom Sheet
```kotlin
var showBottomSheet by remember { mutableStateOf(false) }

if (showBottomSheet) {
    ComposeXModalBottomSheet(
        onDismissRequest = { showBottomSheet = false },
        variant = ComponentVariant.Primary
    ) {
        // Sheet content
    }
}
```

### Other Components

#### Slider
```kotlin
var value by remember { mutableStateOf(50f) }

ComposeXSlider(
    value = value,
    onValueChange = { value = it },
    valueRange = 0f..100f,
    variant = ComponentVariant.Primary
)
```

#### Rating Bar
```kotlin
var rating by remember { mutableStateOf(0f) }

ComposeXRatingBar(
    rating = rating,
    onRatingChange = { rating = it },
    maxStars = 5,
    filledIcon = R.drawable.ic_star_filled,
    unfilledIcon = R.drawable.ic_star_unfilled
)
```

## Theming

### Basic Setup

Wrap your app with `ComposeXTheme`:

```kotlin
ComposeXTheme {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // Your app content
    }
}
```

### Custom Theme

```kotlin
ComposeXTheme(
    darkTheme = false, // Force light theme
    dynamicColor = false, // Disable dynamic colors
    content = { /* content */ }
)
```

### Color System

The design system uses a semantic color system that adapts to themes:

```kotlin
@Composable
fun ColorType.toColor(): Color {
    // Automatically resolves to light or dark variant
    // Defaults to light theme when isLight is null
}
```

**Color Usage:**
- Components automatically use appropriate color variants
- Supports explicit light/dark override
- System theme detection when `isLight = null`

## Customization

### Custom Component Style

Create custom styles using `componentStyleOf()`:

```kotlin
val customStyle = componentStyleOf(
    variant = ComponentVariant.Custom(
        backgroundColor = ColorType.Custom(Color(0xFF6200EE)),
        contentColor = ColorType.Custom(Color.White),
        elevation = ElevationType.High
    ),
    state = ComponentState.Default,
    sizeToken = SizeToken.Large
)
```

### Building Custom Components

Use design tokens to build consistent custom components:

```kotlin
@Composable
fun CustomComponent(
    variant: ComponentVariant = ComponentVariant.Primary,
    sizeToken: SizeToken = SizeToken.Medium
) {
    val style = componentStyleOf(variant = variant, sizeToken = sizeToken)
    val backgroundColor = style.backgroundColor.toColor()
    val shape = style.shape.toShape
    
    Box(
        modifier = Modifier
            .background(backgroundColor, shape)
            .padding(style.spacing.toDp)
    ) {
        // Your custom content
    }
}
```

### Extending Components

All components accept modifiers and can be customized:

```kotlin
ComposeXButton(
    text = "Custom",
    onClick = { },
    modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
    variant = ComponentVariant.Custom(
        backgroundColor = ColorType.Custom(Color.Magenta),
        contentColor = ColorType.Custom(Color.White)
    )
)
```

## Usage Examples

### Complete Form Example

```kotlin
@Composable
fun LoginForm() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ComposeXTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = "Email",
            leadingIcon = R.drawable.ic_email
        )
        
        ComposeXTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = "Password",
            visualTransformation = PasswordVisualTransformation()
        )
        
        ComposeXCheckbox(
            checked = rememberMe,
            onCheckedChange = { rememberMe = it },
            label = "Remember me"
        )
        
        ComposeXButton(
            text = "Login",
            onClick = { /* login */ },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
```

### Component Showcase

```kotlin
@Composable
fun ComponentShowcase() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            ComposeXButton(
                text = "Primary Button",
                onClick = { },
                variant = ComponentVariant.Primary
            )
        }
        
        item {
            ComposeXOutlinedButton(
                text = "Success Outlined",
                onClick = { },
                variant = ComponentVariant.Success
            )
        }
        
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ComposeXChip(
                    text = "Chip 1",
                    onClick = { },
                    variant = ComponentVariant.Info
                )
                ComposeXChip(
                    text = "Chip 2",
                    onClick = { },
                    variant = ComponentVariant.Warning
                )
            }
        }
        
        item {
            ComposeXLinearProgressIndicator(
                progress = 0.65f,
                variant = ComponentVariant.Success
            )
        }
    }
}
```

## Architecture

### Module Structure

```
composex-core/
├── components/          # All UI components
│   ├── button/         # Button variants
│   ├── input/          # TextField
│   ├── selection/      # Checkbox, Radio, Switch, Dropdown, Chip
│   ├── feedback/       # Progress, Badge, Dialog, Snackbar
│   ├── container/      # Card, BottomSheet
│   └── style/          # Component styling system
├── core/
│   ├── configuration/  # Design tokens
│   └── theme/          # Color definitions
└── utils/              # Extensions and utilities
```

### Component Organization

Each component follows a consistent pattern:
- Main composable function (e.g., `ComposeXButton`)
- Supports `variant`, `sizeToken`, and `state` parameters
- Uses `componentStyleOf()` for styling
- Integrates with Material 3 theming
- Accepts standard Compose modifiers

### Design Token System

```
Design Tokens
├── ColorType           # Semantic colors with theme support
├── SizeToken           # Standardized sizing
├── SpacingType         # Consistent spacing
├── ShapeType           # Border radius and shapes
├── ElevationType       # Shadows and elevation
└── FontType            # Typography sizing
```

### Style System

Components use a three-layer styling system:

1. **ComponentVariant**: Defines base visual style
2. **ComponentState**: Handles interactive states
3. **ComponentStyle**: Combines variant + state + size into final style

## Best Practices

1. **Use Design Tokens**: Always use `SizeToken`, `SpacingType`, etc. instead of hardcoded values
2. **Semantic Colors**: Use `ColorType` variants instead of direct colors
3. **Consistent Sizing**: Use appropriate `SizeToken` for your use case
4. **State Management**: Isolate state for each component instance
5. **Theming**: Let the design system handle theme adaptation automatically

## Component Reference

### All Available Components

| Component | Package | Description |
|-----------|---------|-------------|
| `ComposeXButton` | `button` | Filled button with elevation |
| `ComposeXOutlinedButton` | `button` | Button with border outline |
| `ComposeXTextButton` | `button` | Text-only button |
| `ComposeXFilledTonalButton` | `button` | Filled tonal button |
| `ComposeXTextField` | `input` | Text input field |
| `ComposeXCheckbox` | `checkbox` | Checkbox selection |
| `ComposeXRadioButton` | `radio` | Radio button selection |
| `ComposeXSwitch` | `switch` | Toggle switch |
| `ComposeXDropdown` | `dropdown` | Dropdown menu selector |
| `ComposeXChip` | `chip` | Chip with selection support |
| `ComposeXCounter` | `counter` | Increment/decrement counter |
| `ComposeXSlider` | `slider` | Range slider |
| `ComposeXRatingBar` | `rating` | Star rating component |
| `ComposeXCard` | `card` | Material card container |
| `ComposeXElevatedCard` | `card` | Elevated card variant |
| `ComposeXBadge` | `badge` | Notification badge |
| `ComposeXBadgedBox` | `badge` | Badge wrapper component |
| `ComposeXLinearProgressIndicator` | `progress` | Linear progress bar |
| `ComposeXCircularProgressIndicator` | `progress` | Circular progress indicator |
| `ComposeXAlertDialog` | `dialog` | Alert dialog |
| `ComposeXDialog` | `dialog` | Custom dialog |
| `ComposeXModalBottomSheet` | `bottomsheet` | Modal bottom sheet |
| `ComposeXSnackbar` | `snackbar` | Snackbar notification |
| `ComposeXSnackbarHost` | `snackbar` | Snackbar host container |

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

Copyright (c) 2025 Syed Ovais Akhtar

## Contributing

Contributions are welcome! Please ensure all components follow the design system patterns and include proper documentation.

---

**ComposeX Design System** - Built with ❤️ using Jetpack Compose and Material 3

