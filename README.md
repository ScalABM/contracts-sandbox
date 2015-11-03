[![Build Status](https://travis-ci.org/ScalABM/contracts-sandbox.svg)](https://travis-ci.org/ScalABM/contracts-sandbox)
[![Coverage Status](https://coveralls.io/repos/ScalABM/contracts-sandbox/badge.svg?branch=master&service=github)](https://coveralls.io/github/ScalABM/contracts-sandbox?branch=master)
[![Join the chat at https://gitter.im/ScalABM/contracts-sandbox](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/ScalABM/contracts-sandbox?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

# contracts-sandbox

A sandbox for building composable implementations of economic contracts.

## Background an motivation

>To analyze how financial commitments affect the economy it is necessary to look at economic units in terms of their cash flows. The cash-flow approach looks at all units -- be they households, corporations, state and municipal governments, or even national governments -- as if they were banks.

The above quotation is taken from Hyman Minsky's magnum opus *Stabilizing an Unstable Economy*.  Following Minsky,
and more recently Perry Mehrling, we view every economic agent as an entity that is both receiving certain cash flow events (i.e., receipts of various kinds) as well as generating cash flow events (i.e., expenditures of various kinds).  Both Minsky and Mehrling subscribe to the "banking perspective". Within the banking perspective, the most basic constraint that all economic actors face is the *survival constraint*: the inflow of receipt events must be at least as big as the outflow of expenditures events. Put another way, maintaining sufficient liquidity is the primary concern for all economic actors; solvency (i.e., in the positive net worth sense) is a secondary concern.

The time pattern of cash flow events for a particular economic actor will be primarily determined by the various contractual arrangements to which it is a *counterparty*.  The accumulation, across time, of cash flow events for a particular `CounterpartyActor` is captured by its *balance sheet*.

### Requirements for a `CounterpartyActor`
Quick list of current requirements for a `CounterpartyActor`:

* a `CounterpartyActor` should have a `BalanceSheet`,
* a `CounterpartyActor` should be able to process cash flow events.

### Requirements for a `BalanceSheet`
A balance sheet is a module of an economic agent. It is nothing more than a collection of contracts. Each contract has either been issued by or is owned by the agent. In addition to adding and removing contracts, the balance sheet provides an iterator(pattern) method that returns an iterator that iterates over the contracts on the balance sheet that match the supplied pattern. So, for example, iterator(Mortgage.class) would return an iterator that iterates over all the mortgages on the balance sheet.

Quick list of current requirements for a `BalanceSheet`:

* a `BalanceSheet` should have a collection of `ContractActor` objects representing assets, 
* a `BalanceSheet` should have a collection of `ContractActor` objects representing liabilities,
* a `BalanceSheet` should be able to compute its equity.

### Requirements for a `ContractActor`
Quick list of current requirements for a `ContractActor`:

* a `ContractActor` is a `CommunicatingActor`,
* a `ContractActor` should have an issuer for whom the `ContractActor` is a liability, 
* a `ContractActor` should have a counterparty for whom the `ContractActor` is an asset,
* a `ContractActor` should have a `Commitment` specifying the terms of the agreement between the issuer and the counterparty,
* a `ContractActor` should be able to value its `Commitment`.

Conceptually, a `ContractActor` represents a channel for communication between the issuer and the counterparty to the underlying `Commitment`.
