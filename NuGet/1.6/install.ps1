param($installPath, $toolsPath, $package, $project)

$assemblyPath = [System.IO.Path]::Combine($toolsPath, "..\..\lib\net40\Dsrv.Kernpruefung.Deuev-1.6.dll")
$obj = $project.Object
$getRefsMethod = [Microsoft.VisualStudio.Project.VisualC.VCProjectEngine.VCProjectShim].GetMethod("get_References")
$refs = $getRefsMethod.Invoke($obj, $null)
$refs.Add($assemblyPath)