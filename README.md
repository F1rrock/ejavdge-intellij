# EJavdge for IntelliJ IDEA
An IntelliJ IDEA plugin for working with ejudge contests. It
integrates the EJavdge core library to browse problems, download attachments,
test Java solutions locally, and submit the selected source file.

## Requirements
``` 
To build
- JDK 17 to build the plugin.
- IntelliJ IDEA within the declared platform build range: `232` - `242.` (2023.2 through 2024.2).
- The repository includes a Gradle 8.9 wrapper, so a Gradle installation is not required.
```
```
To use
- A compatible ejudge contest and account for actions that access the server.
- A JDK available for compiling and running Java solutions.
```

## Features
Actions are available under **Tools > EJavdge** and in the EJavdge toolbar menu.
Output is displayed in the **EJavdge** tool window.
| Action | Purpose |
| --- | --- |
| Available Problems | List problems in the configured contest. |
| Problem Description | Display the statement for the problem identified in the selected file. |
| Already Solved | List problems already solved by the configured account. |
| Download Attachments | Download the selected problem's attachments to the project directory. |
| Local Testing | Download attachments and run local tests for the selected Java solution. |
| Silent Submit | Submit the selected file and print a confirmation when it has been sent. |

## Create the Plugin Archive
From the plugin repository root, run:
```powershell
.\gradlew.bat buildPlugin
```
On macOS or Linux:
```shell
./gradlew buildPlugin
```
The installable ZIP archive is written to `build/distributions/`. The plugin depends on `org.msubit:EJavdge:1.0-SNAPSHOT`. The version is configured by `ejavdgeVersion` in `gradle.properties`.

## Installation
1. Build the plugin archive.
2. Open **Settings > Plugins** in IntelliJ IDEA.
3. Open the gear menu and select **Install Plugin from Disk**.
4. Select the ZIP from `build/distributions/` and restart the IDE.

## Configuration
Open **Settings > Tools > EJavdge** and configure the connection:
| Setting | Value |
| --- | --- |
| Base URL | The ejudge server hostname or IP address, such as `10.21.17.68`. |
| Port | The server port, such as `80`. |
| Client path | The ejudge client endpoint, such as `/new-client`. |
| Contest ID | The numeric ID of the contest. |
| Login | Your ejudge username. |
| Password | Your ejudge password. |
Click **Apply** and **OK** to save. Connection settings are globalized; the password is stored separately using IntelliJ IDEA's Password Safe.

## Shortcut
1. Open the gear menu and select **Customize Toolbar**.
2. Choose a position you want and click **Add**.
3. Choose **Plugins > Ejavdge**, select an icon and click **Ok**.
4. Click **Apply** and **OK** to save.

## Usage
1. Open a project with a local directory and configure the contest connection.
2. Use **Available Problems** to find the problem identifier.
3. Add a problem marker in your solution file, replacing `A` with target problem identifier:
```java
// # problem A
```
4. Select the solution file in the editor or Project view.
5. Use **Problem Description**, **Download Attachments**, or **Local Testing** as
needed, then use **Silent Submit** to send the solution.
6. Open the **EJavdge** tool window to read command output and local test results.

## Java Example
The following `Main.java` example reads two integers and prints their sum.
Replace `A` with your contest's problem identifier and adapt the solution to
the actual problem statement.
```java
// # problem A
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            long a = input.nextLong();
            long b = input.nextLong();
            System.out.println(a + b);
        }
    }
}
```
Example input:
```text
2 3
```
Expected output:
```text
5
```
Select `Main.java` and use **Local Testing** to run the problem's local tests.
Use **Silent Submit** separately when the solution is ready to submit.

## Project Structure
```text
ejavdge-intellij/
|-- build.gradle.kts         # Plugin build, dependencies, and IDE compatibility
|-- gradle.properties        # Gradle settings and EJavdge core version
|-- settings.gradle.kts      # Project name and plugin repositories
|-- gradlew                  # Gradle wrapper for macOS and Linux
|-- gradlew.bat              # Gradle wrapper for Windows
|-- gradle/wrapper/          # Gradle wrapper JAR and configuration
|-- .run/                    # Sandbox IDE run configuration
|-- README.md
|-- src/main/
    |-- java/org/ejavdge/
    |   |-- action/          # Actions for problems, attachments, tests, and submission
    |   |-- error/           # Plugin-specific errors
    |   |-- event/           # Current project and selected file access
    |   |-- log/             # IntelliJ logging integration
    |   |-- out/             # Console output adapters
    |   |-- scalar/          # Scalar interface for IntelliJ operations
    |   |-- settings/        # Connection configuration and credential storage
    |   |-- widget/          # EJavdge tool window and console
    |-- resources/
        |-- icons/           # Toolbar icon
        |-- META-INF/
            |-- plugin.xml        # Plugin metadata, actions, and extensions
            |-- pluginIcon.svg    # Plugin icon displayed in the IDE's plugin manager
            |-- services/         # service provider registration
```
