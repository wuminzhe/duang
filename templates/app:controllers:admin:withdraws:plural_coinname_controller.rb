module Admin
  module Withdraws
    class [{plural-coinname|capitalize}]Controller < ::Admin::Withdraws::BaseController
      load_and_authorize_resource :class => '::Withdraws::[{singular-coinname|capitalize}]'

      def index
        start_at = DateTime.now.ago(60 * 60 * 24)
        @one_[{plural-coinname}] = @[{plural-coinname}].with_aasm_state(:accepted).order("id DESC")
        @all_[{plural-coinname}] = @[{plural-coinname}].without_aasm_state(:accepted).where('created_at > ?', start_at).order("id DESC")
      end

      def show
      end

      def update
        @[{singular-coinname}].process!
        redirect_to :back, notice: t('.notice')
      end

      def destroy
        @[{singular-coinname}].reject!
        redirect_to :back, notice: t('.notice')
      end
    end
  end
end
