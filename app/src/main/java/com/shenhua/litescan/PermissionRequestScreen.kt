package com.shenhua.litescan

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Created by shenhua on 2025/12/13.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
@Composable
fun PermissionRequestScreen(
    isPermissionRequested: Boolean,
    onRequestClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 1. 显示一个相机的图标提示
        Icon(
            imageVector = Icons.Filled.Notifications,
            contentDescription = "相机权限",
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 2. 标题
        Text(
            text = "需要相机权限",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 3. 解释文本 (Rationale)
        Text(
            text = "为了能够扫描二维码和条形码，LiteScan 需要访问您设备的相机。请在接下来的提示中授予权限。",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 4. 请求权限按钮
        // 点击后会触发 MainActivity 中传入的 launchPermissionRequest()
        Button(
            onClick = onRequestClick,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "授予权限")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 5. 打开设置按钮 (备用方案)
        // 如果用户之前勾选了"不再询问"，标准请求将无效，需要引导去设置页面手动开启。
        if (isPermissionRequested) {
            OutlinedButton(
                onClick = {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts("package", context.packageName, null)
                    }
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "无法弹出提示？去设置打开")
            }
        }
    }
}