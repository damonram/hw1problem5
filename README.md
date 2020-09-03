My project includes User, Wallet, and Product objects. The majority of actions are implemented in the UserService and WalletService.
UserService: User CRUD, assignProduct, transaction
WalletService: Wallet CRUD, viewBalance, incBalance, decBalance, freeze, unfreeze
When a new Product is created, it can be assigned to user using localhost:8080/users/assignProduct/{userId}/{productid}
The product will then belong to the specified user. To make a transaction between users user localhost:8080/users/transaction/{buyerId}/{sellerId}/{productid}
If the seller owns the product, the buyer has enough money, and neither of their wallets are frozen, a transaction will occur between the buyer and seller. The product ownership will be transferred from seller to buyer, and the money will be transferred from buyer to seller. 
A wallet can be frozen with localhost:8080/wallets/freeze/{walletId} and unfrozen with localhost:8080/wallets/unfreeze/{walletId}. When a wallet is frozen, funds can neither be deposited or withdrawn, and transactions cannot be made.
