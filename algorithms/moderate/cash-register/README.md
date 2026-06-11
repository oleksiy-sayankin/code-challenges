# Cash Register

## Description

This challenge calculates the change that a cash register should return to a customer.

The program receives a product price and the amount of cash paid. It compares both values and prints one of the following results:

* `ERROR` if the cash amount is less than the price;
* `ZERO` if the cash amount is equal to the price;
* a comma-separated list of currency denominations if change must be returned.

The solution works with US currency denominations:

```text
PENNY, NICKEL, DIME, QUARTER, HALF DOLLAR,
ONE, TWO, FIVE, TEN, TWENTY, FIFTY, ONE HUNDRED
```

## Input

The input file contains several lines.

Each line contains two decimal numbers separated by a semicolon:

```text
price;cash
```

Example:

```text
15.94;16.00
17.00;16.00
35.00;35.00
45.00;50.00
```

## Output

For each input line, print the result of the cash register operation.

Example:

```text
PENNY,NICKEL
ERROR
ZERO
FIVE
```

## Approach

The solution converts both the price and the cash amount from dollars to cents. This avoids direct floating-point calculations while calculating the change.

Then it handles three cases:

1. If `cash < price`, the result is `ERROR`.
2. If `cash == price`, the result is `ZERO`.
3. If `cash > price`, the program calculates the change.

For the change calculation, the solution uses a greedy approach:

1. Start from the largest available denomination.
2. Take as many units of this denomination as possible.
3. Subtract their value from the remaining change.
4. Continue with the next smaller denomination.
5. Print all used denominations in ascending denomination order.

For example:

```text
45.00;50.00
```

The change is `5.00`, so the output is:

```text
FIVE
```

Another example:

```text
15.94;16.00
```

The change is `0.06`, so the output is:

```text
PENNY,NICKEL
```

## Complexity

Let `d` be the number of available currency denominations.

* Time complexity: `O(d)` for each input line.
* Space complexity: `O(d)` for storing the number of used denominations.

Since the number of denominations is fixed, the solution works in constant time for each input line.
