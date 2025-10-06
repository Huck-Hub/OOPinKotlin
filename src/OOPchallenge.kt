// This is a SINGLE FILE - save it as BankAccount.kt and run with: kotlinc BankAccount.kt -include-runtime -d BankAccount.jar && java -jar BankAccount.jar

// Class declaration with primary constructor
class BankAccount(
    val accountName: String,           // Immutable property: account holder's name
    initialBalance: Double = 0.0       // Constructor parameter with default value of 0.0
) {
    // Property with custom getter/setter - balance can be read publicly but only set privately
    var balance: Double = initialBalance
        private set                     // Only this class can modify balance directly

    // Mutable list to store transaction history as strings
    val transactions: MutableList<String> = mutableListOf()

    // Init block - runs when object is created, after primary constructor
    init {
        // Check if initial balance is positive
        if (initialBalance > 0) {
            // Add initial deposit to transaction history
            // String.format formats the number to 2 decimal places
            transactions.add("Initial deposit: $${String.format("%.2f", initialBalance)}")
        }
    }

    /**
     * Deposit money into the account
     * @param amount The amount to deposit
     * @return true if successful, false otherwise
     */
    fun deposit(amount: Double): Boolean {  // Function that takes Double, returns Boolean
        // Validation: check if amount is positive
        if (amount <= 0) {
            // Print error message to console
            println("Error: Deposit amount must be greater than 0")
            return false                     // Exit function and return false
        }

        // Add amount to current balance using += operator
        balance += amount
        // Record transaction in the list with + for deposit
        transactions.add("Deposit: +$${String.format("%.2f", amount)}")
        // Print success message
        println("Successfully deposited $${String.format("%.2f", amount)}")
        return true                          // Return true to indicate success
    }

    /**
     * Withdraw money from the account
     * @param amount The amount to withdraw
     * @return true if successful, false otherwise
     */
    fun withdraw(amount: Double): Boolean {  // Function that takes Double, returns Boolean
        // First validation: check if amount is positive
        if (amount <= 0) {
            println("Error: Withdrawal amount must be greater than 0")
            return false                     // Exit early if invalid
        }

        // Second validation: check if sufficient funds exist
        if (amount > balance) {
            // Print current balance to show why withdrawal failed
            println("Error: Insufficient funds. Current balance: $${String.format("%.2f", balance)}")
            return false                     // Exit if not enough money
        }

        // Subtract amount from balance using -= operator
        balance -= amount
        // Record transaction with - for withdrawal
        transactions.add("Withdrawal: -$${String.format("%.2f", amount)}")
        // Print success message
        println("Successfully withdrew $${String.format("%.2f", amount)}")
        return true                          // Return true for success
    }

    /**
     * Calculate and return the current balance
     * @return Current account balance
     */
    fun calculateBalance(): Double {
        return balance
    }

    /**
     * Display the current balance
     */
    fun showBalance() {
        println("\n========================================")
        println("Account: $accountName")
        println("Current Balance: $${String.format("%.2f", balance)}")
        println("========================================")
    }

    /**
     * Display all transactions
     */
    fun showTransactions() {
        println("\n========================================")
        println("Transaction History for $accountName")
        println("========================================")
        if (transactions.isEmpty()) {
            println("No transactions yet.")
        } else {
            transactions.forEachIndexed { index, transaction ->
                println("${index + 1}. $transaction")
            }
        }
        println("----------------------------------------")
        println("Current Balance: $${String.format("%.2f", balance)}")
        println("========================================")
    }
}

// Main function - Entry point of the program
fun main() {
    // Print welcome message
    println("========================================")
    println("   WELCOME TO BANK ACCOUNT SYSTEM")
    println("========================================\n")

    // Get account name from user
    print("Enter account holder's name: ")
    val name = readLine() ?: "Unknown"  // readLine() reads user input, ?: provides default if null

    // Get initial balance from user
    print("Enter initial balance (or press Enter for 0): ")
    val initialBalanceInput = readLine()  // Read input as string
    // Convert string to Double, use 0.0 if empty or invalid
    val initialBalance = initialBalanceInput?.toDoubleOrNull() ?: 0.0

    // Create bank account object with user-provided data
    val account = BankAccount(name, initialBalance)

    println("\n✓ Account created successfully!\n")

    // Main program loop - keeps running until user chooses to exit
    var running = true  // Flag to control the loop

    while (running) {  // Loop continues while running is true
        // Display menu options
        println("\n========================================")
        println("           MAIN MENU")
        println("========================================")
        println("1. Deposit")
        println("2. Withdraw")
        println("3. Check Balance")
        println("4. View Transaction History")
        println("5. Exit")
        println("========================================")
        print("Enter your choice (1-5): ")

        // Read user's menu choice
        val choice = readLine()

        // when expression - like switch/case in other languages
        when (choice) {
            "1" -> {  // Deposit option
                print("\nEnter amount to deposit: $")
                val amount = readLine()?.toDoubleOrNull()  // Read and convert to Double
                if (amount != null) {  // Check if conversion was successful
                    account.deposit(amount)  // Call deposit method
                } else {
                    println("Error: Invalid amount entered")
                }
            }

            "2" -> {  // Withdraw option
                print("\nEnter amount to withdraw: $")
                val amount = readLine()?.toDoubleOrNull()  // Read and convert to Double
                if (amount != null) {  // Check if conversion was successful
                    account.withdraw(amount)  // Call withdraw method
                } else {
                    println("Error: Invalid amount entered")
                }
            }

            "3" -> {  // Check balance option
                account.showBalance()  // Call showBalance method
            }

            "4" -> {  // View transactions option
                account.showTransactions()  // Call showTransactions method
            }

            "5" -> {  // Exit option
                println("\n========================================")
                println("Thank you for using Bank Account System!")
                println("Final Balance: $${String.format("%.2f", account.calculateBalance())}")
                println("========================================")
                running = false  // Set flag to false to exit the loop
            }

            else -> {  // If user enters anything other than 1-5
                println("\n✗ Invalid choice. Please enter a number between 1 and 5.")
            }
        }
    }
}