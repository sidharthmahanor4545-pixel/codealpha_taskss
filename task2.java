import random

class Stock:
    def __init__(self, symbol, price):
        self.symbol = symbol
        self.price = price

    def update_price(self):
        change = random.randint(-10, 10)
        self.price = max(1, self.price + change)

class Portfolio:
    def __init__(self):
        self.holdings = {}   # {symbol: quantity}

    def buy(self, stock, quantity):
        if stock.symbol in self.holdings:
            self.holdings[stock.symbol] += quantity
        else:
            self.holdings[stock.symbol] = quantity

    def sell(self, stock, quantity):
        if stock.symbol in self.holdings and self.holdings[stock.symbol] >= quantity:
            self.holdings[stock.symbol] -= quantity
            if self.holdings[stock.symbol] == 0:
                del self.holdings[stock.symbol]
        else:
            print("Not enough shares to sell!")

    def show_portfolio(self):
        print("Your Holdings:", self.holdings)


class User:
    def __init__(self, name, balance):
        self.name = name
        self.balance = balance
        self.portfolio = Portfolio()

    def buy_stock(self, stock, quantity):
        cost = stock.price * quantity
        if self.balance >= cost:
            self.balance -= cost
            self.portfolio.buy(stock, quantity)
            print("Stock bought successfully!")
        else:
            print("Not enough balance!")

    def sell_stock(self, stock, quantity):
        if stock.symbol in self.portfolio.holdings:
            revenue = stock.price * quantity
            self.portfolio.sell(stock, quantity)
            self.balance += revenue
            print("Stock sold successfully!")
        else:
            print("You don't own this stock!")

    def show_balance(self):
        print("Balance:", self.balance)


stock1 = Stock("AAPL", 150)
stock2 = Stock("TSLA", 700)

user = User("John", 10000)

print("Market Prices:")
print(stock1.symbol, stock1.price)
print(stock2.symbol, stock2.price)

user.buy_stock(stock1, 10)
user.buy_stock(stock2, 2)


stock1.update_price()
stock2.update_price()

print("\nUpdated Market Prices:")
print(stock1.symbol, stock1.price)
print(stock2.symbol, stock2.price)

user.portfolio.show_portfolio()
user.show_balance()