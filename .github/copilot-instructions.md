## Project quick orientation

This repository is a small Java teaching project (CMSC 131) organized for development inside a VS Code devcontainer.
Target audience: automated coding assistants that will edit, refactor, or add code and tests.

Key locations
- `src/` — Java source by package. Example project: `src/projects/bank` (core code to inspect).
- `test/` — JUnit tests mirroring `src/` packages (e.g. `test/projects/bank/BankTest.java`). Tests define expected behavior and are the primary spec.
- `data/` — CSV fixtures used by the bank project (e.g. `data/accountsRead.csv`, `data/accountsWrite.csv`).
- `.devcontainer/` and `README.md` — environment and developer instructions (useful to know how to run things).

Big-picture architecture and data flow (what to look for)
- The `projects.bank` package implements an in-memory array-backed `Bank` that reads account/transaction CSVs and mutates `Account` objects.
- Data flow: CSV -> `Bank.loadAccounts` / `processTransactions` -> `Transaction` objects -> `Account` updates. Key constants: `Bank.READ_FILE` and `Bank.WRITE_FILE` point at `data/` files.
- Tests in `test/projects/bank` use the CSV fixtures as inputs; for correct behavior you must make `loadAccounts`, `addAccount`, `findID`, and `writeAccounts` conform to test expectations (e.g., `BankTest` expects `getCount()` == 392 after loading `Bank.READ_FILE`).

Developer workflows and how-to (explicit)
- Use the VS Code devcontainer (README steps). The container provides Java/JDK and the Java Test Runner. Prefer the IDE play buttons and Testing pane for quick runs.
- Sanity checks (in container): `java --version` and `javac --version` as shown in `README.md`.
- Run a single class `main` for quick smoke: open `src/projects/bank/Main.java` and use the Run Java action in the editor; the project is not wired to a build tool (Maven/Gradle) so prefer the editor-runner or compile/run using the container's Java tooling.
- Run tests via the Testing pane (JUnit 5). Tests live under `test/`; modify code in `src/` and run tests to verify behavior.

Project-specific conventions and gotchas
- Packages follow `projects.*` and tests mirror that package structure under `test/` — do not change package declarations when moving files.
- Many classes contain TODOs and are partially implemented or contain deliberate errors; tests are the source of truth. Inspect tests to learn required behavior before changing production code.
- File paths in code are relative (e.g. `data/accountsRead.csv`). When writing tests or new code that reads/writes files, use those paths or the `Bank.READ_FILE` / `Bank.WRITE_FILE` constants so tests can find fixtures.
- The repo assumes a Linux container (devcontainer). Code comments sometimes use `\n` vs `System.lineSeparator()` — keep cross-platform concerns in mind if you change environment-sensitive behavior.

Where to start when adding features or debugging
1. Open the relevant test in `test/` — it usually shows the expected API and behavior.
2. Open the implementation in `src/` and search for matching TODOs or failing assertions.
3. Run the specific test in VS Code. Fix the minimum code to satisfy tests and keep changes small and well-scoped.

Examples of repository-specific patterns
- CSV-based factory: `Account.createAccount(String token)` — many code paths expect CSV tokenization like `TYPE,ID,NAME,BALANCE`.
- Use of constants: `Bank.READ_FILE` and `Bank.WRITE_FILE` are used by tests and `Main`; prefer these constants when adding file-based behaviors.

When making edits
- Keep package names and file locations consistent with the current layout.
- Run local tests after edits; prefer small, focused commits.
- If you touch file I/O, add or update tests that read from `data/` fixtures so CI/grader behavior remains reproducible.

If anything is unclear or you need more examples from the codebase, tell me which file(s) you want referenced and I will update this guidance.
