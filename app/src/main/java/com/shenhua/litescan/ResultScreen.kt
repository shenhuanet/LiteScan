package com.shenhua.litescan

import android.util.Patterns
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.shenhua.litescan.ui.theme.LiteScanTheme

/**
 * Created by shenhua on 2025/12/13.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    scanResult: String,
    onBackToScan: () -> Unit
) {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    LiteScanTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("扫描结果") },
                    navigationIcon = {
                        IconButton(onClick = onBackToScan) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "返回扫描"
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "扫描结果:",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                SelectionContainer {
                    Text(
                        text = scanResult,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )
                }

                val isLink = Patterns.WEB_URL.matcher(scanResult).matches()
                if (isLink) {
                    Button(onClick = {
                        uriHandler.openUri(scanResult)
                    }) {
                        Text("打开链接")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Button(
                    onClick = onBackToScan,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text("返回并重新扫描")
                }
            }
        }
    }
}