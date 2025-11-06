def knapsack_dp():
    n = int(input("Enter number of items: "))

    weights = []
    values = []

    print("\nEnter weight and value (profit) for each item separated by space:")
    for i in range(n):
        while True:
            try:
                w, v = map(int, input(f"Item {i+1}: ").split())
                weights.append(w)
                values.append(v)
                break
            except ValueError:
                print("Invalid input. Please enter two integers separated by space.")

    W = int(input("\nEnter maximum capacity of knapsack: "))

    # DP table initialization
    dp = [[0 for _ in range(W + 1)] for _ in range(n + 1)]

    # DP computation
    for i in range(1, n + 1):
        for w in range(1, W + 1):
            if weights[i - 1] <= w:
                include = values[i - 1] + dp[i - 1][w - weights[i - 1]]
                exclude = dp[i - 1][w]
                dp[i][w] = max(include, exclude)
            else:
                dp[i][w] = dp[i - 1][w]

    print("\nMaximum profit that can be obtained:", dp[n][W])

if __name__ == "__main__":
    knapsack_dp()
