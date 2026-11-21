# Changelog

## [1.1.0] - 2026-11-21

### Added
- **Probed Submit** runs the local samples before submitting the original source.
  A failed local check stops the submission.
- Local checks use the problem's optional Java header and footer, such as
  `HeaderOfA.java` and `FooterOfA.java`, for all sample runs.
- Added screenshots and a guide covering plugin setup and contest workflows.

### Changed
- Contest actions run in the background so you can keep using the editor.
  Failures are shown in an EJavdge error dialog.
- Updated the plugin and tool window icons.
- **Download Attachments** is now **Download Local Test Files**. It saves
  files linked for local testing into the selected solution's Sources Root.
  **Local Probe** and **Probed Submit** use that same working directory.
  If you added the old download action to your toolbar, add it again under
  its new name.
- The plugin bundles EJavdge core `v1.0.2` from JitPack. The build uses the
  Gradle 9.8.0 wrapper and targets IntelliJ IDEA 2023.3 (build 233) or newer.

### Fixed
- Download problem headers and footers using the correct ejudge action.
- Keep the statement's `Notes` section out of the expected sample output.

## [1.0.0] - 2026-09-30

### Added
- Initial release
