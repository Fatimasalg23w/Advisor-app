# Script de compilacion para Tour Advisor
Write-Host "Compilando Tour Advisor..." -ForegroundColor Cyan

# Verificar que existe la carpeta lib
if (-not (Test-Path "lib")) {
    Write-Host "ERROR: No existe la carpeta lib con los JARs de MongoDB" -ForegroundColor Red
    exit 1
}

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

Write-Host "Todos los JARs encontrados" -ForegroundColor Green

# Crear directorio de salida si no existe
if (-not (Test-Path "out")) {
    New-Item -ItemType Directory -Path "out" | Out-Null
    Write-Host "Directorio out creado" -ForegroundColor Gray
}

# Limpiar archivos .class anteriores
if (Test-Path "out\*.class") {
    Remove-Item "out\*.class" -Force
    Write-Host "Archivos .class antiguos eliminados" -ForegroundColor Gray
}

# Construir classpath
$classpath = ($requiredJars -join ";") + ";out"
Write-Host "Classpath configurado" -ForegroundColor Gray

# Obtener todos los archivos Java
$javaFiles = Get-ChildItem -Path "src\main\java" -Filter "*.java" -File

if ($javaFiles.Count -eq 0) {
    Write-Host "ERROR: No se encontraron archivos .java en src\main\java" -ForegroundColor Red
    exit 1
}

Write-Host "Compilando $($javaFiles.Count) archivos Java..." -ForegroundColor Cyan

# Crear lista de archivos para compilar
$fileList = $javaFiles | ForEach-Object { $_.FullName }

# Compilar todos los archivos
javac -encoding UTF-8 -cp $classpath -d out @fileList

if ($LASTEXITCODE -eq 0) {
    Write-Host "Compilacion exitosa!" -ForegroundColor Green
    Write-Host "Ejecuta el programa con: .\run.ps1" -ForegroundColor Cyan
} else {
    Write-Host "Error en la compilacion" -ForegroundColor Red
    exit 1
}