SOLID Refactor — Concise Reference (Exercises 1–10)

Instructions: For each exercise below you'll find:
- Initial (before refactor): which file(s) held the problematic logic
- Final (after refactor): the files present now in the repo for that exercise
- Aim: what the exercise asked you to achieve
- Principle: which SOLID principle guided the refactor
- What we did: a short, simple one- or two-line summary

---

Exercise 01 — Student Onboarding
- Initial (before): `OnboardingService.java` contained a single god-method that parsed input, validated, generated id, saved and printed.
- Final (now): `InputParser.java`, `StudentValidator.java`, `StudentRepository.java`, `FakeDb.java`, `OnboardingService.java`, `StudentRecord.java`, `IdUtil.java`, `TextTable.java`, `Demo01.java`
- Aim: separate concerns so each change (parsing, validation, storage, presentation) touches one place.
- Principle: SRP (Single Responsibility)
- What we did: extracted parsing and validation, introduced a repository interface, kept service focused on creating/saving a record, and moved presentation to `TextTable`.
- Example: `InputParser.parse("name=Joe;age=20")` -> `StudentRecord`; `OnboardingService.create(record)` saves via `StudentRepository`.

- Files (one-line each):
	- `OnboardingService.java`: original god-method (parse → validate → id → save → print) before refactor.
	- `InputParser.java`: parse raw CLI/input strings into `StudentRecord`.
	- `StudentValidator.java`: validate fields and business rules for a record.
	- `StudentRepository.java`: repository interface for persisting student records.
	- `FakeDb.java`: in-memory implementation of `StudentRepository` for demos.
	- `StudentRecord.java`: simple DTO holding student data.
	- `IdUtil.java`: generate unique student identifiers.
	- `TextTable.java`: format records into a readable table for display.
	- `Demo01.java`: demo runner showing onboarding flow.

---

Exercise 02 — Cafeteria Billing
- Initial (before): `CafeteriaSystem.checkout()` mixed calculation, invoice formatting and persistence.
- Final (now): `CafeteriaSystem.java`, `MenuItem.java`, `OrderLine.java`, `TaxRules.java`, `DiscountRules.java`, `InvoiceFormatter.java`, `InvoiceStore.java`, `FileStore.java`, `Demo02.java`
- Aim: separate calculation, formatting and storage so each can change independently.
- Principle: SRP (Single Responsibility)
- What we did: kept billing logic in `CafeteriaSystem`, moved text building to `InvoiceFormatter`, and hid persistence behind `InvoiceStore`.
- Example: `CafeteriaSystem.checkout(order)` returns totals; pass to `InvoiceFormatter.format()` to get printable invoice.

- Files (one-line each):
	- `CafeteriaSystem.java`: calculates order totals and applies tax/discount rules.
	- `MenuItem.java`: model for a cafeteria menu item and its price.
	- `OrderLine.java`: one line of an order (item + quantity).
	- `TaxRules.java`: encapsulates tax calculation logic.
	- `DiscountRules.java`: encapsulates discount computation logic.
	- `InvoiceFormatter.java`: builds printable invoice text from totals.
	- `InvoiceStore.java`: abstraction for persisting invoices.
	- `FileStore.java`: file-backed implementation of `InvoiceStore`.
	- `Demo02.java`: demo runner for checkout and invoice flows.

---

Exercise 03 — Placement Eligibility
- Initial (before): `EligibilityEngine` contained a long if/else-if chain implementing all rules.
- Final (now): `EligibilityRule.java`, `DisciplinaryRule.java`, `CgrRule.java`, `AttendanceRule.java`, `CreditsRule.java`, `EligibilityEngine.java`, `EligibilityResult.java`, `StudentProfile.java`, `ReportPrinter.java`, `FakeEligibilityStore.java`, `Demo03.java`
- Aim: make it easy to add new eligibility rules without editing the engine.
- Principle: OCP (Open/Closed)
- What we did: defined a `EligibilityRule` interface and implemented each check as a small class; engine iterates a list of rules.
- Example: `engine.evaluate(profile)` runs `new CreditsRule().check(profile)` and others, returning an `EligibilityResult`.

- Files (one-line each):
	- `EligibilityEngine.java`: orchestrates running all registered `EligibilityRule`s.
	- `EligibilityRule.java`: contract for a single eligibility check.
	- `DisciplinaryRule.java`: checks disciplinary flags on `StudentProfile`.
	- `CgrRule.java`: verifies cumulative grade requirement.
	- `AttendanceRule.java`: verifies attendance thresholds.
	- `CreditsRule.java`: verifies credit accumulation rules.
	- `EligibilityResult.java`: aggregates pass/fail outcomes and reasons.
	- `StudentProfile.java`: data holder used by rules.
	- `ReportPrinter.java`: formats eligibility results for output.
	- `FakeEligibilityStore.java`: demo store for eligibility-related data.
	- `Demo03.java`: demo runner showing rules evaluation.

---

Exercise 04 — Hostel Fee Calculator
- Initial (before): room pricing and add-on prices were hard-coded with switch/if statements inside the calculator.
- Final (now): `RoomPricing.java`, `AddOnPricing.java`, `BookingRequest.java`, `AddOn.java`, `HostelFeeCalculator.java`, `Money.java`, `ReceiptPrinter.java`, `LegacyRoomTypes.java`, `FakeBookingRepo.java`, `Demo04.java`
- Aim: make pricing data-driven so new room types or add-ons can be added without code edits.
- Principle: OCP (Open/Closed)
- What we did: moved prices into maps with `register()` methods and let the calculator look up prices, keeping computation separate from pricing data.
- Example: `RoomPricing.register("deluxe", Money.of(5000)); hostelFeeCalculator.calculate(request)` looks up prices.

- Files (one-line each):
	- `HostelFeeCalculator.java`: computes total hostel fees using pricing registries.
	- `RoomPricing.java`: registry and lookup for room-type prices.
	- `AddOnPricing.java`: registry and lookup for add-on prices.
	- `BookingRequest.java`: request model describing a booking.
	- `AddOn.java`: model representing an add-on option.
	- `Money.java`: value object for monetary amounts and arithmetic.
	- `ReceiptPrinter.java`: formats fee/booking receipts for output.
	- `LegacyRoomTypes.java`: compatibility mapping for older room identifiers.
	- `FakeBookingRepo.java`: demo repository for storing bookings.
	- `Demo04.java`: demo runner for fee calculation.

---

Exercise 05 — File Exporter Hierarchy
- Initial (before): exporters behaved inconsistently (some threw, some truncated, some returned different types) requiring caller special-casing.
- Final (now): `Exporter.java`, `ExportRequest.java`, `ExportResult.java`, `PdfExporter.java`, `CsvExporter.java`, `JsonExporter.java`, `SampleData.java`, `Demo05.java`
- Aim: provide a uniform exporter contract so any exporter can be used interchangeably.
- Principle: LSP (Liskov Substitution)
- What we did: introduced `ExportResult` so exporters return success/failure uniformly; callers check the result instead of handling exceptions per subtype.
- Example: `ExportResult r = pdfExporter.export(req); if (r.isSuccess()) { /* saved */ } else { /* show r.error */ }`

- Files (one-line each):
	- `Exporter.java`: exporter interface defining the `export(...)` contract.
	- `ExportRequest.java`: describes what/where to export.
	- `ExportResult.java`: uniform result object with success/failure metadata.
	- `PdfExporter.java`: PDF export implementation.
	- `CsvExporter.java`: CSV export implementation.
	- `JsonExporter.java`: JSON export implementation.
	- `SampleData.java`: sample dataset used by demos/tests.
	- `Demo05.java`: demo runner exercising different exporters.

---

Exercise 06 — Notification Senders
- Initial (before): senders threw exceptions or silently changed messages; callers needed try/catch or special logic for channels.
- Final (now): `NotificationSender.java`, `SendResult.java`, `Notification.java`, `AuditLog.java`, `EmailSender.java`, `SmsSender.java`, `WhatsAppSender.java`, `Demo06.java`
- Aim: make sending uniform across channels so callers can invoke senders without special-case handling.
- Principle: LSP (Liskov Substitution)
- What we did: standardized `send()` to return `SendResult` (ok/fail) and used an `AuditLog` for recordings; failures are returned rather than thrown.
- Example: `SendResult r = emailSender.send(notification); auditLog.record(r);` — caller inspects `r` instead of catching.

- Files (one-line each):
	- `NotificationSender.java`: channel sender interface declaring `send()`.
	- `SendResult.java`: standardized outcome for send attempts (ok/fail + reason).
	- `Notification.java`: message model containing subject/body/metadata.
	- `AuditLog.java`: records send attempts and results for auditing.
	- `EmailSender.java`: email channel implementation.
	- `SmsSender.java`: SMS channel implementation.
	- `WhatsAppSender.java`: WhatsApp channel implementation.
	- `Demo06.java`: demo runner showing unified sending flow.

---

Exercise 07 — Classroom Device Controller
- Initial (before): controller code risked coupling to concrete device classes and their mixed capabilities.
- Final (now): `Powerable.java`, `InputConnectable.java`, `Scannable.java`, `BrightnessControl.java`, `TemperatureControl.java`, `DeviceRegistry.java`, `LightsPanel.java`, `AirConditioner.java`, `Projector.java`, `AttendanceScanner.java`, `ClassroomController.java`, `Demo07.java`
- Aim: let controller operate on device capabilities rather than concrete implementations.
- Principle: ISP (Interface Segregation) and SRP
- What we did: introduced small capability interfaces; devices implement only what they support and controller uses those interfaces.
- Example: `if (device instanceof Powerable) ((Powerable)device).powerOn();` — controller programs to capability interfaces.

- Files (one-line each):
	- `Powerable.java`: capability interface for powering devices on/off.
	- `InputConnectable.java`: capability interface for managing input connections.
	- `Scannable.java`: capability interface for attendance scanning devices.
	- `BrightnessControl.java`: capability interface for controlling brightness.
	- `TemperatureControl.java`: capability interface for temperature adjustments.
	- `DeviceRegistry.java`: discovers and provides devices to the controller.
	- `LightsPanel.java`: concrete lights implementation.
	- `AirConditioner.java`: concrete AC implementation.
	- `Projector.java`: concrete projector implementation.
	- `AttendanceScanner.java`: concrete scanner implementation.
	- `ClassroomController.java`: orchestrates devices via capability interfaces.
	- `Demo07.java`: demo runner for device control flows.

---

Exercise 08 — Club Event Tools
- Initial (before): role-specific tasks (minutes, budgeting, events) were tangled in monolithic classes.
- Final (now): `EventOps.java`, `MinutesOps.java`, `FinanceOps.java`, `SecretaryTool.java`, `TreasurerTool.java`, `EventLeadTool.java`, `MinutesBook.java`, `BudgetLedger.java`, `EventPlanner.java`, `ClubConsole.java`, `Demo08.java`
- Aim: provide focused tools per role and separate domain concerns.
- Principle: ISP (Interface Segregation) and SRP
- What we did: split functionality into role-specific tools and operation classes; storage like minutes and ledger are dedicated classes.
- Example: `SecretaryTool.recordMinutes(minutes)` updates `MinutesBook`; `TreasurerTool.addExpense(entry)` updates `BudgetLedger`.

- Files (one-line each):
	- `EventOps.java`: encapsulates event-related operations.
	- `MinutesOps.java`: encapsulates operations on meeting minutes.
	- `FinanceOps.java`: encapsulates financial operations and ledgers.
	- `SecretaryTool.java`: UI/tool surface for secretary responsibilities.
	- `TreasurerTool.java`: UI/tool surface for treasurer responsibilities.
	- `EventLeadTool.java`: UI/tool surface for event leads.
	- `MinutesBook.java`: storage of meeting minutes.
	- `BudgetLedger.java`: storage and ledger logic for budgets/expenses.
	- `EventPlanner.java`: coordinates event planning details.
	- `ClubConsole.java`: wires role tools into a console UI.
	- `Demo08.java`: demo runner for role-based workflows.

---

Exercise 09 — Grading & Plagiarism Pipeline
- Initial (before): grading, checking and report writing were tightly coupled in a single flow.
- Final (now): `Grader.java`, `CodeGrader.java`, `PlagiarismChecker.java`, `Rubric.java`, `Submission.java`, `EvaluationPipeline.java`, `ReportWriter.java`, `Writer.java`, `Checker.java`, `Demo09.java`
- Aim: split the grading process into clear stages and depend on abstractions.
- Principle: SRP (Single Responsibility) and DIP (Dependency Inversion)
- What we did: separated pipeline stages (checking, grading, reporting) and wired them via interfaces so components can be swapped or tested independently.
- Example: `pipeline.run(submission)` -> `PlagiarismChecker`, `CodeGrader`, then `ReportWriter` produce final report object.

- Files (one-line each):
	- `Grader.java`: grading abstraction for scoring submissions.
	- `CodeGrader.java`: concrete code-grading implementation.
	- `PlagiarismChecker.java`: checks submissions for plagiarism.
	- `Rubric.java`: grading rubric model used by graders.
	- `Submission.java`: model for a student's submission.
	- `EvaluationPipeline.java`: composes pipeline stages (check → grade → report).
	- `ReportWriter.java`: formats and writes the evaluation report.
	- `Writer.java`: generic output writer abstraction.
	- `Checker.java`: generic checker abstraction for pipeline stages.
	- `Demo09.java`: demo runner showing pipeline execution.

---

Exercise 10 — Transport Booking Service
- Initial (before): allocation, distance calculation, payment and receipt generation were handled together in booking code.
- Final (now): `TransportBookingService.java`, `TripRequest.java`, `DriverAllocator.java`, `Allocator.java`, `DistanceCalculator.java`, `DistanceService.java`, `PaymentGateway.java`, `Payment.java`, `BookingReceipt.java`, `ConsoleUi.java`, `Main.java`
- Aim: separate concerns so each booking subtask is a replaceable component.
- Principle: SRP (Single Responsibility) and DIP (Dependency Inversion)
- What we did: factored allocator, distance service, payment gateway and receipt generator into separate components; booking service orchestrates them.
- Example: `bookingService.book(request)` calls `distanceService.calc(req)`, `allocator.allocate(req)`, `paymentGateway.pay(payment)` and returns `BookingReceipt`.

- Files (one-line each):
	- `TransportBookingService.java`: orchestrates booking using allocator, distance and payment components.
	- `TripRequest.java`: model containing trip/request details.
	- `DriverAllocator.java`: concrete algorithm for selecting drivers.
	- `Allocator.java`: abstraction for driver allocation strategies.
	- `DistanceCalculator.java`: computes distance/fare estimates.
	- `DistanceService.java`: external adapter for distance calculations.
	- `PaymentGateway.java`: abstraction for processing payments.
	- `Payment.java`: payment/transaction model.
	- `BookingReceipt.java`: receipt model returned after booking.
	- `ConsoleUi.java`: simple console UI for booking flows.
	- `Main.java`: application entry point/demo runner.

---

How to use this file
- Read the exercise you want to explain. Each section lists the main files you can open to show code examples.

If you want, I can now:
- convert this to markdown with a linked table of contents, or
- generate a one-page quick-memorize sheet (3-line summary per exercise).

*** End of file
