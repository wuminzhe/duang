module Private
  module Deposits
    class [{plural-coinname|capitalize}]Controller < ::Private::Deposits::BaseController
      include ::Deposits::CtrlCoinable
    end
  end
end
