module Deposits
  class [{singular-coinname|capitalize}] < ::Deposit
    include ::AasmAbsolutely
    include ::Deposits::Coinable
  end
end
