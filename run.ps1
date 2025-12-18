# Script para ejecutar Tour Advisor
Write-Host "Ejecutando Tour Advisor..." -ForegroundColor Cyan

# Verificar que existe la carpeta out con archivos compilados
if (-not (Test-Path "out")) {
    Write-Host "ERROR: No existe la carpeta out" -ForegroundColor Red
    Write-Host "Ejecuta primero: .\compile.ps1" -ForegroundColor Yellow
    exit 1
}

# Verificar que existen archivos .class
$classFiles = Get-ChildItem -Path "out" -Filter "*.class" -File
if ($classFiles.Count -eq 0) {
    Write-Host "ERROR: No hay archivos compilados en out/" -ForegroundColor Red
    Write-Host "Ejecuta primero: .\compile.ps1" -ForegroundColor Yellow
    exit 1
}

Write-Host "Archivos compilados encontrados: $($classFiles.Count)" -ForegroundColor Green

# Verificar que existen los JARs
$requiredJars = @(
    "lib\mongodb-driver-sync-4.11.1.jar",
    "lib\mongodb-driver-core-4.11.1.jar",
    "lib\bson-4.11.1.jar",
    "lib\gson-2.10.1.jar"
)

foreach ($jar in $requiredJars) {
    if (-not (Test-Path $jar)) {
        Write-Host "ERROR: Falta el archivo $jar" -ForegroundColor Red
        exit 1
    }
}

# Construir classpath
$classpath = ($requiredJars -join ";") + ";out"

# Ejecutar la aplicación
Write-Host "Iniciando aplicacion..." -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Gray
java -cp $classpath CompleteTourAdvisorApp