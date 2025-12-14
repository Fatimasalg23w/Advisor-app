# run.ps1

# Limpiar compilaciones anteriores
Write-Host "Limpiando compilaciones anteriores..." -ForegroundColor Yellow
Remove-Item -Path "bin\*.class" -ErrorAction SilentlyContinue
New-Item -ItemType Directory -Force -Path "bin" | Out-Null

# Compilar
Write-Host "Compilando aplicación..." -ForegroundColor Cyan
javac --module-path "C:\javafx-sdk-25.0.1\lib" `
      --add-modules javafx.controls,javafx.fxml `
      -d bin `
      src\main\java\*.java

# Verificar compilación
if ($LASTEXITCODE -eq 0) {
    Write-Host "Compilación exitosa" -ForegroundColor Green
    Write-Host "Iniciando aplicación..." -ForegroundColor Cyan
    Write-Host ""
    
    # Ejecutar
    java --module-path "C:\javafx-sdk-25.0.1\lib" `
         --add-modules javafx.controls,javafx.fxml `
         -cp bin `
         AdvisorApp
} else {
    Write-Host "Error en la compilación" -ForegroundColor Red
    exit 1
}
