/*
Copyright 2015 David R. Pugh, J. Doyne Farmer, and Dan F. Tang

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package settlement

import scala.collection.immutable

import akka.actor.{Props, ActorRef, Actor}


/** Central counterparty (CCP) clearing mechanism.
  *
  * @note The key difference between CCP settlement and bilateral settlement is that CCP inserts
  *       itself as the counterparty to both the ask and the bid trading parties before
  *       processing the final transaction. By acting as a counterparty on every transaction the
  *       CCP effectively assumes all counterparty risk.
  */
class CCPSettlementMechanism extends Actor with SettlementMechanismLike with CounterpartyLike {

  /* BilateralClearingMechanism can be used to process novated fills. */
  val bilateralSettlementMechanism: ActorRef = context.actorOf(Props[BilateralSettlementMechanism])

  /** Central counter-party (CCP) settlement mechanism behavior. */
  val settlementMechanismBehavior: Receive = {
    case contract: ContractLike =>
      val novatedContracts = novate(contract)
      novatedContracts foreach(novatedContract => bilateralSettlementMechanism ! novatedContract)
  }

  /** Novate a FillLike between two trading counterparties.
    *
    * @note The substitution of counterparties is typically accomplished through a legal process
    *       called contract novation. Novation discharges the contract between the original
    *       trading counterparties and creates two new, legally binding contracts – one between
    *       each of the original trading counterparties and the central counterparty.
    * @param contract a ContractLike object.
    * @return a list of two ContractLikes - one between each of the original trading counterparties
    *         and the central counterparty.
    */
  def novate(contract: ContractLike): immutable.Seq[ContractLike] = {
    ???
  }

  def receive: Receive = {
    settlementMechanismBehavior orElse ???
  }

}