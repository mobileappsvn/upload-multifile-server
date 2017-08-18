<html>
<head>
<title>Multiple File Upload Demo Application</title>
</head>
<body>
    <form action="api/upload/multipleFiles" method="POST"
        enctype="multipart/form-data">
        <div id="fileSection">
            <h1>Multiple File Upload Demo Application</h1>
            <table>
                <tr>
                    <td>Candidate Name:</td>
                    <td><input type="text" name="candidateName" size="45" />
                    </td>
                </tr>
                <tr>
                    <td>Candidate Info File:</td>
                    <td><input type="file" name="infoFile" size="45" />
                    </td>
                </tr>
                <tr>
                    <td>Candidate's Photo:</td>
                    <td><input type="file" name="imgFile" size="45" />
                    </td>
                </tr>
            </table>
        </div>
        <p />
        <input type="submit" value="Upload" />
    </form>
</body>
</html>