module Withdraws
  class [{singular-coinname|capitalize}] < ::Withdraw
    include ::AasmAbsolutely
    include ::Withdraws::Coinable
    include ::FundSourceable
  end
end
