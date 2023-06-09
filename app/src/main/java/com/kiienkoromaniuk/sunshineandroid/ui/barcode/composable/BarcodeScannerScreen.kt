package com.kiienkoromaniuk.sunshineandroid.ui.barcode.composable

import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.common.util.concurrent.ListenableFuture
import com.kiienkoromaniuk.sunshineandroid.R
import com.kiienkoromaniuk.sunshineandroid.ui.barcode.BarCodeAnalyser
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@ExperimentalGetImage
@Composable
fun BarcodeScannerScreen(navController: NavController) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var preview by remember { mutableStateOf<Preview?>(null) }
    val barCodeVal = remember { mutableStateOf("") }
    var showGif by remember { mutableStateOf(true) }
    var isBarcodePassed by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        if (!showGif && !isBarcodePassed) {
            navController.previousBackStackEntry
                ?.savedStateHandle
                ?.set("barcode", barCodeVal.value)
            isBarcodePassed = true
            navController.navigateUp()
        }

        AndroidView(

            factory = { AndroidViewContext ->
                PreviewView(AndroidViewContext).apply {
                    this.scaleType = PreviewView.ScaleType.FILL_CENTER
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                    )
                    implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            update = { previewView ->

                val cameraSelector: CameraSelector = CameraSelector.Builder()
                    .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                    .build()
                val cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()
                val cameraProviderFuture: ListenableFuture<ProcessCameraProvider> =
                    ProcessCameraProvider.getInstance(context)

                cameraProviderFuture.addListener({
                    preview = Preview.Builder().build().also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }
                    val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
                    val barcodeAnalyser = BarCodeAnalyser { barcodes ->
                        barcodes.forEach { barcode ->
                            barcode.rawValue?.let { barcodeValue ->
                                barCodeVal.value = barcodeValue

                                if (barcodeValue.length >= 10) {
                                    showGif = !showGif
                                }
                            }
                        }
                    }
                    val imageAnalysis: ImageAnalysis = ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                        .also {
                            it.setAnalyzer(cameraExecutor, barcodeAnalyser)
                        }

                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector,
                            preview,
                            imageAnalysis,
                        )
                    } catch (e: Exception) {
                    }
                }, ContextCompat.getMainExecutor(context))
            },
        )
    }
}

@Composable
fun SuccessAnimation() {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.success),
    )
    LottieAnimation(
        composition = composition,
        // progress = { /*TODO*/ },
        iterations = 2,
    )
}

@Composable
fun FailureAnimation() {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.failure),
    )
    LottieAnimation(
        composition = composition,
        // progress = { /*TODO*/ },
        iterations = 2,
    )
}
