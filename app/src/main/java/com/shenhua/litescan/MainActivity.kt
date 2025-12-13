package com.shenhua.litescan

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@ExperimentalPermissionsApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val cameraPermissionState = rememberPermissionState(
                android.Manifest.permission.CAMERA,
                onPermissionResult = {
                    if (it) {
                        navController.navigate("scanner")
                    }
                }
            )
            if (cameraPermissionState.status.isGranted) {
                AppNavHost(navController = navController)
            } else {
                LaunchedEffect(Unit) {
                    cameraPermissionState.launchPermissionRequest()
                }
                PermissionRequestScreen(
                    isPermissionRequested = true,
                    onRequestClick = { cameraPermissionState.launchPermissionRequest() }
                )
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "scanner"
    ) {
        composable("scanner") {
            QrCodeScannerScreen(
                onScanResult = { result ->
                    navController.navigate("result/${Uri.encode(result)}")
                }
            )
        }
        composable(
            "result/{scanResult}",
            arguments = listOf(
                navArgument("scanResult") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val result = backStackEntry.arguments?.getString("scanResult") ?: "扫描失败"
            ResultScreen(
                scanResult = result,
                onBackToScan = { navController.popBackStack() }
            )
        }
    }
}