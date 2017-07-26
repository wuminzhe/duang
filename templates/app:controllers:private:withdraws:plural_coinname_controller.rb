module Private::Withdraws
  class [{plural-coinname|capitalize}]Controller < ::Private::Withdraws::BaseController
    include ::Withdraws::Withdrawable
  end
end
